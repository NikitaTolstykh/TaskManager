# TaskManager
## Project Description

Task Manager is a simple console application for managing tasks. With this manager, you can add, edit, delete, and view tasks. Each task includes a description, a due date, and a priority flag (true/false). The program saves tasks to a CSV file so your data persists even after the application is closed.

## Purpose and Technologies

The goal of this project is to practice working with:

- Java basics:  
  - methods,
  - working with strings
  - data input,  
  - multidimensional arrays,  
  - reading files,  
  - writing data to files,  
  - importing and using external libraries.  
- The **Apache Commons Lang** library for easier manipulation of arrays and numerical data.
- Project build in maven

## How to Run the Project

1. **Clone the repository:**

   git clone https://github.com/NikitaTolstykh/TaskManager.git
   cd task-manager
2. **Open the project in an IDE (e.g., Intellij IDEA):**
   
    After opening, the project will automatically download dependencies specified in the pom.xml file.

3. **Build and run the project with the IDE:**
   
    Locate the Main.java file and run it.


## Features

**Key Functionality:**

- **Add tasks:** create a new task with a description, due date, and priority flag.
- **View task list:** display all tasks in a user-friendly format.
- **Edit tasks:** modify the description, due date, or priority of an existing task.
- **Remove tasks:** delete a task by its number in the list.
- **Input validation:**
  - When selecting a task for editing or removal, the program ensures the input is a valid number and not negative.
- **Save tasks:** upon exiting the application, all changes are saved to the `tasks.csv` file.

## Example Usage

 **When the program starts you will see the menu:**   
Welcome to the task manager
Please select one of the following options
add
list
edit
remove
exit

Choose a command and follow the instructions. For example:

- The **add** command will prompt you to enter the task's description, due date, and priority.
- The **list** command will display all current tasks:

    1:Go to work, 2024-12-30, true
  
    2:Change haircut, 2024-01-10, false


Upon exiting, the tasks will be saved to the `tasks.csv` file, which can be opened in any spreadsheet editor.


