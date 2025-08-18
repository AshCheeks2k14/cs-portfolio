// ProjectTwo.cpp
// Ashley Almanza
// CS300 - Advising Assistance Program
// Purpose: Command-line program to manage course info for advisors


#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>
#include <cctype>

using namespace std;

struct Course {
    string number;                 // e.g., "CSCI200"
    string title;                  // e.g., "Data Structures"
    vector<string> prerequisites;  // e.g., {"CSCI101"}
};

// ---------- helpers ----------
static inline string ltrim(const string& s) {
    size_t i = 0;
    while (i < s.size() && isspace(static_cast<unsigned char>(s[i]))) ++i;
    return s.substr(i);
}

static inline string rtrim(const string& s) {
    if (s.empty()) return s;
    size_t i = s.size() - 1;
    while (i < s.size() && isspace(static_cast<unsigned char>(s[i]))) {
        if (i == 0) return "";
        --i;
    }
    return s.substr(0, i + 1);
}

static inline string trim(const string& s) { return rtrim(ltrim(s)); }

static inline string upper(string s) {
    for (char& c : s) c = static_cast<char>(toupper(static_cast<unsigned char>(c)));
    return s;
}

// Split by comma. (Input file in project does not include quoted commas.)
static vector<string> splitCSV(const string& line) {
    vector<string> out;
    string field;
    stringstream ss(line);
    while (getline(ss, field, ',')) out.push_back(trim(field));
    return out;
}

// Case-insensitive alphanumeric comparator (keeps numbers like CSCI100 < CSCI101 < CSCI200, etc.)
static bool courseNumLess(const string& a, const string& b) {
    string A = upper(a), B = upper(b);
    return A < B;
}

// ---------- core logic ----------
class Catalog {
   public:
    bool loaded = false;

    // Key: course number (uppercased)  -> value: Course
    unordered_map<string, Course> byNum;

    // Load courses from CSV; returns true on success.
    bool loadFromCSV(const string& filename, string& errorMessage) {
        ifstream file(filename);
        if (!file.is_open()) {
            errorMessage = "Could not open file: " + filename;
            return false;
        }

        unordered_map<string, Course> temp;
        string line;
        size_t lineNo = 0;

        while (getline(file, line)) {
            ++lineNo;
            line = trim(line);
            if (line.empty()) continue;

            vector<string> cols = splitCSV(line);
            if (cols.size() < 2) {
                errorMessage = "Invalid format on line " + to_string(lineNo) + ": expected at least 2 columns.";
                return false;
            }

            Course c;
            c.number = trim(cols[0]);
            c.title  = trim(cols[1]);

            for (size_t i = 2; i < cols.size(); ++i) {
                string p = trim(cols[i]);
                if (!p.empty()) c.prerequisites.push_back(p);
            }

            if (c.number.empty()) {
                errorMessage = "Missing course number on line " + to_string(lineNo) + ".";
                return false;
            }

            temp[upper(c.number)] = c;  // overwrite duplicates with last occurrence
        }

        byNum.swap(temp);
        loaded = true;
        return true;
    }

    // Return sorted list of course numbers (alphanumeric, case-insensitive).
    vector<string> sortedCourseNumbers() const {
        vector<string> keys;
        keys.reserve(byNum.size());
        for (const auto& kv : byNum) keys.push_back(kv.second.number);  // keep original casing
        sort(keys.begin(), keys.end(), courseNumLess);
        return keys;
    }

    // Print the entire course list.
    void printCourseList() const {
        if (!loaded) {
            cout << "Please load the data first (Option 1).\n";
            return;
        }
        cout << "\nHere is a sample schedule:\n\n";
        for (const string& num : sortedCourseNumbers()) {
            const Course& c = byNum.at(upper(num));
            cout << c.number << ", " << c.title << '\n';
        }
        cout << '\n';
    }

    // Print specific course info with prerequisites (numbers and titles if available).
    void printCourseInfo(const string& rawInput) const {
        if (!loaded) {
            cout << "Please load the data first (Option 1).\n";
            return;
        }
        string key = upper(trim(rawInput));
        auto it = byNum.find(key);
        if (it == byNum.end()) {
            cout << "Course not found: " << rawInput << '\n';
            return;
        }
        const Course& c = it->second;

        cout << c.number << ", " << c.title << '\n';
        cout << "Prerequisites: ";
        if (c.prerequisites.empty()) {
            cout << "None\n\n";
            return;
        }

        // Print each prerequisite with number and title if known.
        for (size_t i = 0; i < c.prerequisites.size(); ++i) {
            const string& pnum = c.prerequisites[i];
            auto pit = byNum.find(upper(pnum));
            if (pit != byNum.end()) {
                cout << pit->second.number << " (" << pit->second.title << ")";
            } else {
                // Show number even if not in catalog (robust to partial data).
                cout << pnum;
            }
            if (i + 1 < c.prerequisites.size()) cout << ", ";
        }
        cout << "\n\n";
    }
};

// ---------- UI ----------
static void printMenu() {
    cout << "1. Load Data Structure.\n";
    cout << "2. Print Course List.\n";
    cout << "3. Print Course.\n";
    cout << "9. Exit\n\n";
    cout << "What would you like to do? ";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cout << "Welcome to the course planner.\n\n";

    Catalog catalog;

    while (true) {
        printMenu();

        string choiceStr;
        if (!getline(cin, choiceStr)) break;  // EOF
        choiceStr = trim(choiceStr);

        if (choiceStr.empty()) {
            cout << "Please enter a menu option.\n\n";
            continue;
        }

        // Validate numeric input
        int choice = -1;
        try {
            choice = stoi(choiceStr);
        } catch (...) {
            cout << choiceStr << " is not a valid option.\n\n";
            continue;
        }

        if (choice == 1) {
            cout << "Enter the file name for the course data (e.g., CS 300 ABCU_Advising_Program_Input.csv):\n> ";
            string filename;
            if (!getline(cin, filename)) break;
            filename = trim(filename);
            if (filename.empty()) {
                cout << "File name cannot be empty.\n\n";
                continue;
            }

            string err;
            if (catalog.loadFromCSV(filename, err)) {
                cout << "Data loaded successfully from '" << filename << "'.\n\n";
            } else {
                cout << "Error: " << err << "\n\n";
            }

        } else if (choice == 2) {
            catalog.printCourseList();

        } else if (choice == 3) {
            cout << "What course do you want to know about?\n> ";
            string query;
            if (!getline(cin, query)) break;
            cout << '\n';
            catalog.printCourseInfo(query);

        } else if (choice == 9) {
            cout << "Thank you for using the course planner!\n";
            break;

        } else {
            cout << choice << " is not a valid option.\n\n";
        }
    }
    return 0;
}
