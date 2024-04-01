# *IronInsight*

## Strength training personal benchmark/progress tracker

**Project Description**:
- This application will be a strength training tracking system. 
Users will be able to record their goals and details of their training sessions.
- The application can be used by gym-goers of with any level of experience, but is specifically 
targeted towards those who are serious about long-term strength goals.
- This project is of interest to me because I am an avid fitness enthusiast and gym *enjoyer*
 
**User Stories**:
- As a user, I want to be able to add goals, training records, or personal best results to my training log.
- As a user, I want to be able to record my progress on any exercise.
- As a user, I want to be able to get a list of all my goals and training records
- As a user, I want to be able to calculate my 1-repetition maximum based on the weight I am currently training with.
- As a user, I want to be able to mark a goal as completed.
- As a user, I want to be able to save all my logs and goals if I wish to, from the main menu.
- As a user, I want to be able to choose to load my logs from a file upon starting the program, if I wish to.

## Instructions for Grader

- You can generate the first required action related to adding goals, training records, or personal best results 
to a training log by clicking on the Home tab and using the text fields to specify personal bests, regular 
workout records, or goals then clicking the "add" button. You can add arbitrarily many of each.
- You can generate the second required action related to adding log entries by going to the goals tab, which allows you 
to mark goals as completed. 
- Another related action is the option to filter log entries by date using the search bar in the Progress tab.
- You can find all the log entries of different types listed in the Progress tab, where you can view details.
- You can locate my visual component by adding a log entry or a goal, or by marking a goal completed, 
which creates an image popup. 
- You can save the state of my application by clicking on "File" in the menu bar at the top, and selecting 
"save logs to file."
- You can reload the state of my application by clicking on "File" in the menu bar at the top, and selecting 
"load logs from file."

## Phase 4: Task 2
Sun Mar 31 11:59:09 PDT 2024  
Training logs initiated.  
Sun Mar 31 11:59:09 PDT 2024  
Goals retrieved  
Sun Mar 31 11:59:15 PDT 2024  
Loaded 3 log entries from file.  
Sun Mar 31 12:00:03 PDT 2024  
Exercise named Single Arm Lat Pulldown added to Pull Workout  
Sun Mar 31 12:00:03 PDT 2024  
Exercise named Seated Row added to Pull Workout  
Sun Mar 31 12:00:03 PDT 2024  
Record named Pull Workout added to logs.  
Sun Mar 31 12:00:59 PDT 2024  
Target date for Lat Pulldown Target set as 2024-04-20  
Sun Mar 31 12:00:59 PDT 2024  
Exercise named Lat Pulldown added to Lat Pulldown Target  
Sun Mar 31 12:00:59 PDT 2024  
Goal named Lat Pulldown Target added to logs.  
Sun Mar 31 12:01:10 PDT 2024  
Goals retrieved  
Sun Mar 31 12:01:16 PDT 2024  
Goal named New Bench Target marked as completed.  
Sun Mar 31 12:01:19 PDT 2024  
Saved 5 log entries to file.

## Phase 4: Task 3
One refactoring I could do that would decrease the coupling between the UI 
package and the model package would be to remove the association between Page
and LoggerGUI. This was originally designed so that instances of the 3 subclasses (Home, Progress, Goals)
could call methods on LoggerGUI which then called methods on LogManager to modify its logs. However, 
there is no need for Page and its subclasses to know about LoggerGUI, since
all the functionality they need is in LogManager (e.g. adding, retrieving, marking as completed etc.)  
  
I could instead make Page have a field of LogManager, and make LogManager a singleton
so that there is just one instance that is accessed by Page and its subclasses. The Page constructor would
then call getInstance() on LogManager and store it as a protected field. I could then
also remove the association between LoggerGUI and LogManager, and any methods in LoggerGUI that 
operate on LogManager. This would increase the cohesion of LoggerGUI as well. Hence, the three subtypes of Page
could just operate on LogManager directly, simplifying the application.
  
Another refactoring that would make the hierarchy in the UI package more logical would be to have 
Goal and PersonalBest extend **LogEntry** instead of **Record**. This is because they don't really make sense
as subclasses of Record and I only made it that way so that I could inherit the functionality relating to 
adding exercises from the Record class. However, PersonalBest should only have one instance of Exercise
while Record can have arbitrarily many. This violates the LSP since PersonalBest has a narrower precondition than 
its superclass Record. Therefore, it would make more sense to have Record, PersonalBest, and Goal all extend 
LogEntry. Maybe record could also be renamed to TrainingRecord or Workout to make it more understandable.