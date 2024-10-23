**Problem Statement**

  Develop a TODO task-tracking application that allows its users to maintain TODO lists. TODO tasks in the list can have a deadline and tags for easier grouping and management. You can demonstrate the working of the application using a driver program or necessary test cases. There is no requirement to use a database for persistence (instead, use memory to store data) or write a web service.

**Features**

  Users should be able to update the TODO list at any point in time - add, modify, and remove tasks
  A task can be marked as completed and once it is completed, it is automatically removed from the TODO list.
  Tasks can also be added to appear in the TODO list at a future date.
  Users should be able to see an activity log that describes additions, modifications, and removals of tasks from the TODO list during a particular time period.
  Users should also be able to see statistics around how many tasks were added, completed, and spilled over the deadline during a particular time period.

**Implementation requirements**

  Your solution should implement the following functions. Feel free to use the representation for objects you deem fit for the problem and the provided use cases.

    addTask(task)
    getTask(taskId) -> a task
    modifyTask(task)
    removeTask(taskId)
    listTasks(...) -> a list of tasks that match the given filter ordered based on a defined sort criteria
    getStatistics(optional[timePeriod]) -> statistics for the given time period (if supplied)
    getActivityLog(optional[timePeriod]) -> activity log for the given time period (if supplied)

**Things to keep in mind**

  You are only allowed to use in-memory data structures
  You are NOT allowed to use any databases
  You are NOT required to have a full-fledged web service or APIs exposed
  A main class/driver program/test cases that simulate the above operations can be used to showcase how the requirements work.
  Please ensure you submit your solution on time even if it is incomplete. A partial solution submitted on time is better than a complete solution submitted after time.
