# CS 340 Client/Server Development

## Grazioso Salvare Rescue Dashboard

This portfolio artifact represents my work developing an interactive dashboard for Grazioso Salvare. The project uses Python, MongoDB, Dash, and a reusable CRUD module to retrieve and display animal shelter data.

## Reflection

### How do you write programs that are maintainable, readable, and adaptable?

I write maintainable, readable, and adaptable programs by separating different responsibilities into organized modules and functions. In this course, the CRUD Python module from Project One was especially useful because it separated the database operations from the dashboard code. Instead of writing MongoDB commands throughout the dashboard, I was able to call reusable methods from the AnimalShelter class. This made the code easier to understand, test, and modify.

Using the CRUD module in Project Two also made the application more adaptable because changes to database operations could be made in one location without rewriting the entire dashboard. In the future, I could reuse this type of CRUD module in other applications that need to create, read, update, or delete records from a MongoDB database. It could also be expanded to support additional queries or serve as the data-access layer for another user interface.

### How do you approach a problem as a computer scientist?

I approach a problem by first identifying the client's requirements and then breaking the problem into smaller pieces that can be developed and tested individually. For the Grazioso Salvare project, I had to consider both the database requirements and how users would interact with the information through the dashboard. I worked with database queries, filtering options, the interactive data table, visualizations, and geolocation information to make sure the different components worked together correctly.

This project differed from some of my previous assignments because I was not only writing code to produce a specific result. I was developing multiple components that depended on one another while also meeting the requirements of a client. In future projects, I would continue breaking requirements into smaller tasks, testing individual components, using reusable modules, and verifying that the finished application meets the client's needs.

### What do computer scientists do, and why does it matter?

Computer scientists use technology and programming to solve problems, organize information, automate processes, and create tools that help people make better decisions. This work matters because software can transform large amounts of information into something that is easier and faster for people to understand and use.

For a company such as Grazioso Salvare, the dashboard developed in this course could make it much faster to identify dogs that meet specific search-and-rescue training requirements. Instead of manually searching through large amounts of animal shelter data, employees can filter records and view useful information through an interactive table, breed chart, and geolocation map. This allows the organization to spend less time searching through data and more time focusing on its rescue and training work.
