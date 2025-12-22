# cs-portfolio

Reflection
What was the problem you were solving in the projects for this course?
In these projects, I was tasked with analyzing and implementing different data structures to manage and display course information. The first project focused on evaluating runtime and memory usage, while the second required building a program to sort and print courses in alphanumeric order.
How did you approach the problem?
I approached the problems by first reviewing how each data structure (vector, hash table, binary search tree) worked, and then comparing their efficiency in terms of runtime and memory. For the implementation, I designed algorithms that leveraged these data structures to store and retrieve course data effectively. Understanding data structures was crucial because they directly impact how efficiently data can be accessed, modified, and displayed.
How did you overcome any roadblocks?
One challenge I faced was debugging issues in my C++ code, especially with tree traversal and memory handling. I overcame this by breaking the program into smaller pieces, testing each part, and using debugging tools in Visual Studio. I also reviewed documentation and course resources to clarify concepts.
How has your work on this project expanded your approach to designing software and developing programs?
This project helped me see the importance of selecting the right data structure for the problem. It reinforced that software design isn’t just about writing code that works—it’s about writing code that works efficiently and can scale.
How has your work on this project evolved the way you write programs that are maintainable, readable, and adaptable?
Through these projects, I have learned to use consistent naming, clear comments, and modular design. I also recognized the value of structuring code so that future changes—such as adding new features or handling larger datasets—can be made more easily. This will help me write programs that are easier to maintain and adapt over time



## Artemis Financial Project Reflection

### Client Summary
Artemis Financial is a financial services company that needed stronger security for its web-based software. They were concerned about potential vulnerabilities in their current application, especially around data integrity, secure communication, and outdated dependencies. My job was to analyze their codebase, identify vulnerabilities, and improve the software’s overall security posture.

### Security Work & What I Did Well
I did well at systematically identifying vulnerabilities using tools such as OWASP Dependency-Check and manual code review. I focused on secure coding principles—like validating input, strengthening cryptographic functions, and enforcing HTTPS—to ensure the application handled sensitive data safely. Writing secure code is vital because one weak point can compromise an entire system. Strong software security protects users, preserves trust, and reduces the risk of costly breaches that can damage a company financially and reputationally.

### Most Challenging or Helpful Part
The most challenging part of the assessment was analyzing dependency vulnerabilities and determining which issues were true threats versus low-risk or false positives. This strengthened my understanding of how external libraries affect the security of an entire application.

### How I Added Layers of Security
I increased security by implementing HTTPS with a self-signed certificate, updating vulnerable dependencies, improving cryptographic hashing, and refactoring insecure code. In the future, I would continue using tools like OWASP Dependency-Check, static analyzers, and OWASP Top 10 guidelines to evaluate vulnerabilities and choose mitigation methods.

### Ensuring Functionality and Security
After refactoring, I rebuilt and ran the application to confirm functionality, then reran Dependency-Check to verify no new vulnerabilities were introduced. I also checked that HTTPS worked correctly. This combination of functional testing and repeated scanning validated both stability and security.

### Helpful Tools and Practices
Tools and practices I will continue using include:
- OWASP Dependency-Check  
- Keytool for generating HTTPS certificates  
- Secure coding principles such as input validation and encryption  
- Static analysis and dependency updates  

### Portfolio Value
For future employers, I can showcase:
- A completed vulnerability assessment or secure software report  
- Evidence of implementing HTTPS  
- Proof that I can identify and mitigate security risks  
- Experience refactoring real-world application code for security  

These artifacts demonstrate practical secure software development and DevSecOps skills.
