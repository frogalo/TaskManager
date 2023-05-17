package com.frogalo.taskmanager;

import com.frogalo.taskmanager.entity.*;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.client.result.InsertOneResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class TaskManagerApplication {

    public static void main(String[] args) {
        String connectionString = "mongodb://localhost:27017";
        MongoClientURI uri = new MongoClientURI(connectionString);
        try (MongoClient mongoClient = MongoClients.create(String.valueOf(uri))) {
            MongoDatabase database = mongoClient.getDatabase("taskmanager");
            MongoCollection<BasicDBObject> taskCollection = database.getCollection("tasks", BasicDBObject.class);

// Create a Task object
            Task task = new Task();
            task.setName("Sample Task");
            task.setDescription("This is a sample task");
            task.setStartDate(new Date());
            task.setEndDate(new Date());
            task.setStatus("Pending");

            // Convert the Task object to a BasicDBObject
            BasicDBObject taskDocument = new BasicDBObject()
                    .append("name", task.getName())
                    .append("description", task.getDescription())
                    .append("startDate", task.getStartDate())
                    .append("endDate", task.getEndDate())
                    .append("status", task.getStatus());

            // Insert the document into the collection
            InsertOneOptions insertOptions = new InsertOneOptions();
            InsertOneResult taskInsertResult = taskCollection.insertOne(taskDocument, insertOptions);

            // Get the generated ID of the inserted document
            String taskId = taskInsertResult.getInsertedId().toString();
            task.setId(taskId);

            // Create a Category object
            Category category = new Category("Sample Category", "This is a sample category");

            // Convert the Category object to a BasicDBObject
            BasicDBObject categoryDocument = new BasicDBObject()
                    .append("name", category.getName())
                    .append("description", category.getDescription());

            // Insert the document into the collection
            InsertOneResult categoryInsertResult = taskCollection.insertOne(categoryDocument, insertOptions);

            // Get the generated ID of the inserted document
            String categoryId = categoryInsertResult.getInsertedId().toString();
            category.setId(categoryId);

            // Create an UpdateComment object
            User user = new User("John", "Doe", "john.doe@example.com", "user", null);
            UpdateComment updateComment = new UpdateComment("Update comment", new Date(), user, task, "updateCommentId", "In Progress", "Some update description");

            // Convert the UpdateComment object to a BasicDBObject
            BasicDBObject updateCommentDocument = new BasicDBObject()
                    .append("content", updateComment.getContent())
                    .append("createdAt", updateComment.getCreatedAt())
                    .append("user", createUserDBObject(updateComment.getUser()))
                    .append("task", createTaskDBObject(updateComment.getTask()))
                    .append("updateCommentId", updateComment.getUpdateCommentId())
                    .append("updateStatus", updateComment.getUpdateStatus())
                    .append("updateDescription", updateComment.getUpdateDescription());

            // Insert the document into the collection
            InsertOneResult updateCommentInsertResult = taskCollection.insertOne(updateCommentDocument, insertOptions);

            // Get the generated ID of the inserted document
            String updateCommentId = updateCommentInsertResult.getInsertedId().toString();
            updateComment.setUpdateCommentId(updateCommentId);

            // Create a Project object
            List<Task> tasks = new ArrayList<>();
            tasks.add(task);
            Project project = new Project("Sample Project", "This is a sample project", new Date(), new Date(), tasks);

            // Convert the Project object to a BasicDBObject
            BasicDBObject projectDocument = new BasicDBObject()
                    .append("name", project.getName())
                    .append("description", project.getDescription())
                    .append("startDate", project.getStartDate())
                    .append("endDate", project.getEndDate())
                    .append("tasks", createTaskDocuments(project.getTasks()));

            // Insert the document into the collection
            InsertOneResult projectInsertResult = taskCollection.insertOne(projectDocument, insertOptions);

            // Get the generated ID of the inserted document
            String projectId = projectInsertResult.getInsertedId().toString();
            project.setId(projectId);

            // Create a User object
            // Create sample comments
            User newUser = new User("Jane", "Smith", "jane.smith@example.com", "user");
            BasicDBObject userDocument = new BasicDBObject()
                    .append("firstName", newUser.getFirstName())
                    .append("lastName", newUser.getLastName())
                    .append("email", newUser.getEmail())
                    .append("role", newUser.getRole());

// Insert the document into the collection
            InsertOneResult userInsertResult = taskCollection.insertOne(userDocument, insertOptions);

            Comment comment1 = new Comment("Comment 1", new Date(), newUser, task, null, "1");
            Comment comment2 = new Comment("Comment 2", new Date(), newUser, task, "1", null);


// Add comments to the comment list
            List<Comment> commentList = new ArrayList<>();
            commentList.add(comment1);
            commentList.add(comment2);

// Add task to the task set
            Set<Task> taskSet = new HashSet<>();
            taskSet.add(task);


// Create a new User object
            User newUser2 = new User("Jane", "Smith", "jane.smith@example.com", "user", commentList, taskSet);

// Convert the User object to a BasicDBObject
            BasicDBObject userDocument2 = new BasicDBObject()
                    .append("firstName", newUser.getFirstName())
                    .append("lastName", newUser.getLastName())
                    .append("email", newUser.getEmail())
                    .append("role", newUser.getRole())
                    .append("comments", createBasicDBList(newUser.getComments()))
                    .append("tasks", createBasicDBList(newUser.getTasks()));

// Insert the document into the collection
            InsertOneResult userInsertResult2 = taskCollection.insertOne(userDocument2, insertOptions);


            // Get the generated ID of the inserted document
            String userId = userInsertResult.getInsertedId().toString();
            newUser.setId(userId);

            System.out.println("Objects inserted into the database successfully!");
        }
    }

    private static BasicDBObject createUserDBObject(User user) {
        BasicDBObject userDocument = new BasicDBObject()
                .append("firstName", user.getFirstName())
                .append("lastName", user.getLastName())
                .append("email", user.getEmail())
                .append("role", user.getRole());

        // Convert the comments to BasicDBList
        BasicDBList commentList = new BasicDBList();
        for (Comment comment : user.getComments()) {
            commentList.add(createCommentDBObject(comment));
        }
        userDocument.append("comments", commentList);

        // Convert the tasks to BasicDBList
        BasicDBList taskList = new BasicDBList();
        for (Task task : user.getTasks()) {
            taskList.add(createTaskDBObject(task));
        }
        userDocument.append("tasks", taskList);

        return userDocument;
    }

    private static BasicDBList createBasicDBList(Collection<?> collection) {
        BasicDBList basicDBList = new BasicDBList();
        for (Object item : collection) {
            basicDBList.add(createBasicDBObject(item));
        }
        return basicDBList;
    }

    private static BasicDBObject createBasicDBObject(Object object) {
        if (object instanceof Comment) {
            return createCommentDBObject((Comment) object);
        } else if (object instanceof Task) {
            return createTaskDBObject((Task) object);
        } else if (object instanceof User) {
            return createUserDBObject((User) object);
        } else {
            throw new IllegalArgumentException("Unsupported object type: " + object.getClass());
        }
    }

    private static BasicDBObject createCommentDBObject(Comment comment) {
        BasicDBObject commentDocument = new BasicDBObject()
                .append("content", comment.getContent())
                .append("createdAt", comment.getCreatedAt())
                .append("user", createBasicDBObject(comment.getUser()))
                .append("task", createBasicDBObject(comment.getTask()))
                .append("commentId", comment.getId());
        return commentDocument;
    }

    private static BasicDBObject createTaskDBObject(Task task) {
        return new BasicDBObject()
                .append("name", task.getName())
                .append("description", task.getDescription())
                .append("startDate", task.getStartDate())
                .append("endDate", task.getEndDate())
                .append("status", task.getStatus());
    }

    private static List<BasicDBObject> createTaskDocuments(List<Task> tasks) {
        List<BasicDBObject> taskDocuments = new ArrayList<>();
        for (Task task : tasks) {
            BasicDBObject taskDocument = createTaskDBObject(task);
            taskDocuments.add(taskDocument);
        }
        return taskDocuments;
    }


    private static BasicDBObject createProjectDBObject(Project project) {
        return new BasicDBObject()
                .append("name", project.getName())
                .append("description", project.getDescription())
                .append("startDate", project.getStartDate())
                .append("endDate", project.getEndDate())
                .append("tasks", createTaskDocuments(project.getTasks()));
    }
}

