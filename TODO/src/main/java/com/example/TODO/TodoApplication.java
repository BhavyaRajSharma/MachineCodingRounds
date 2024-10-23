package com.example.TODO;




import java.time.LocalDateTime;
import java.util.Arrays;

//@SpringBootApplication
public class TodoApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(TodoApplication.class, args);
//	}

	public static void main(String[] args) {
		TaskManager taskManager = new TaskManager();

		// Adding tasks
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("ADDING TASK");
		taskManager.addTask("Buy groceries", LocalDateTime.now(), LocalDateTime.now().plusDays(1), Arrays.asList("shopping"));
		taskManager.addTask("Finish homework",LocalDateTime.now(), LocalDateTime.now().plusDays(2), Arrays.asList("school"));
		taskManager.addTask("Machine Coding Interview Round",LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), Arrays.asList("Interview","Jobs"));
		System.out.println("TASKS ADDED");
		System.out.println("---------------------------------------------------------------------------------------------------");

		// Listing tasks
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("LISTING TASK");
		System.out.println("Current Tasks: " + taskManager.listTasks(SortBy.DEADLINE, true));
		System.out.println("---------------------------------------------------------------------------------------------------");

		// Modifying a task
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("Current Tasks: " + taskManager.getTask(1));
		System.out.println("MODIFYING TASK");
		taskManager.modifyTask(1, "Buy groceries and cook dinner", LocalDateTime.now(), LocalDateTime.now().plusDays(1), Arrays.asList("shopping", "cooking"));
		System.out.println("Current Tasks: " + taskManager.getTask(1));
		System.out.println("---------------------------------------------------------------------------------------------------");



		// Completing a task
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("Current Tasks: " + taskManager.getTask(2));
		System.out.println("COMPLETING TASK");
		taskManager.completeTask(2);
		System.out.println("Current Tasks: " + taskManager.getTask(2));
		System.out.println("---------------------------------------------------------------------------------------------------");


		// Activity log
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("ACTIVITY LOG");
		System.out.println("Activity Log: " + taskManager.getActivityLog(null,null));
		System.out.println("---------------------------------------------------------------------------------------------------");

		// Statistics
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.println("STATISTICS");
		System.out.println("Statistics: " + taskManager.getStatistics(null,null));
		System.out.println("---------------------------------------------------------------------------------------------------");
	}

}
