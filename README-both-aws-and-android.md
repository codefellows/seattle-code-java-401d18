# Code 401: Java Guide Repo

Welcome to the guide repo for Code 401: Java! This document will give you an overview of the course and resources you might need. The facilitator guide in the curriculum directory has the pre-course-launch checklist and some more curriculum-specific information.

## Resources

- [This guide](./) contains most of the pieces of curriculum you need to run the course, mainly in the `curriculum` directory. In each day's directory, you can find the readings due on that day, the README that contains an agenda for lecture on that day, and the lab assignment to be done after that lecture.
- [The Code 401: Java Prework Guide](https://github.com/codefellows/code-401-java-prework) contains the prework that students must complete before beginning the course. It's iframed into Canvas, so you shouldn't need to share that link directly with students unless they're having trouble accessing Canvas.
- [The Common Curriculum guide repo](https://github.com/codefellows/common_curriculum) contains the data structures & algorithms content, including readings, lecture content, and lab content for DS&A labs (for each lecture/lab pair divisible by 5). It also contains the daily code challenges.
    - It also contains the career coaching content, common prework pieces, and the like.
- [The Canvas template](https://canvas.instructure.com/courses/1453652) can be imported, along with the [common curriculum Canvas template](https://canvas.instructure.com/courses/1294324), to create a Canvas course that contains pre-built assignments (labs/readings/code challenges/career coaching/everything).
- [The Code Challenges repo](https://github.com/codefellows/code-challenges) is an ad-hoc repo that contains assorted code challenges, used for final whiteboard interviews and whiteboarding in front of the class. Note that it's not a "gold-standard" repo, so you should take everything in there with a grain of salt.
- The other Code 401 guides at CodeFellows can be helpful when looking for inspiration on labs or on ways to teach a concept.
    - [Code 401: JavaScript](https://github.com/codefellows/code-401-javascript-guide) is the oldest and therefore most-complete course repo.
    - [Code 401: Dotnet](https://github.com/codefellows/code-401-dotnet-guide) shares the structure of the first several weeks with Java.
    - [Code 401: Python](https://github.com/codefellows/code-401-python-guide) has the most content on deployment/devops.


## Course Outline

### Week 1: Basic Java language
* Day 1: Basics of data types, basics of classes/objects, exceptions, debugging, development environment
* Day 2: Arrays, loops, ArrayLists, imports, testing
* Day 3: Maps, primitives vs. objects, file I/O, try/catch
* Day 4: Intro OOP, more classes and objects and methods, bitmap
* Day 5: LinkedLists
### Week 2: Lots of Java
* Day 6: Inheritance, extends, super, intro to interfaces
* Day 7: Interfaces v2, List vs ArrayList, Map vs. HashMap, composition vs. inheritance
* Day 8: Class design practice
* Day 9: To the interwebs! WRRC review, using Java to make requests
* Day 10: Stacks and queues
### Week 3: Full-stack apps, including ORM/DB
* Day 11: Basic web apps with Spring
* Day 12: Spring RESTful routing and connecting to Postgres
* Day 13: One-to-many joins
* Day 14: Many to many joins (can be combined with day 13 for career coaching)
* Day 15: binary trees
### Week 4: Web App Auth
* Day 16: What is auth, bcrypt basics
* Day 17: Spring Security with bcrypt
* Day 18: Spring Security rules & access controls
* Day 19: Talk about web sockets; review
* Day 20: Project kickoff
### Week 5: Midterm projects
* Days 21-24: Working on projects
* Day 25: Midterm project presentations
### Week 6: Intro to Android: Basics; communicating with a provided TaskMaster backend in Java
* Day 26: Android Studio WYSIWYG editor, Activities, event listeners
* Day 27: Intents, XML files, SharedPreferences
* Day 28: mobile DB, web requests (start consuming TaskMaster)
* Day 29: RecyclerViews and ViewAdapters
* Day 30: review, Graphs
### Week 7: Intro to AWS: Lambda, DynamoDB, S3; labs build towards replacing backend of TaskMaster
* Day 31: Lambdas with API Gateway for basic routes
* Day 32: DynamoDB with Lambda for read
* Day 33: DynamoDB with Lambda for create; fully replace Spring TaskMaster
* Day 34: S3 for bucket storage, HashTables
* Day 35: Career coaching
### Week 8: More AWS: CI/CD with CodePipeline, CloudFormation, start integrating Android/AWS
* Day 36: Continuous Integration and Deployment
* Day 37: Deployment Pipelines and CloudFormation
* Day 38: Message Queues: SNS and SQS
* Day 39: Cognito for login across Android/AWS
* Day 40: Career coaching
### Week 9: Android/AWS Integration
* Day 41: Espresso testing, polishing Cognito integration
* Day 42: Forms and posting data
* Day 43: SNS integration
* Day 44: Location, payments
* Day 45: Project kickoff
### Week 10: final projects
* Days 46-49: Working on final projects
* Day 50: Final project presentations, graduation

## Paragraphs: The Second Half of the Course
In the second half of this course, we'll cover two important modern uses of Java: AWS and Android. In our first two weeks after midterm projects, we begin with an intro to three of the core technologies that are part of most AWS applications: Lambda, DynamoDB, and S3. We'll use those technologies to create a task-tracking API, and use continuous integration and continuous deployment (CI/CD) to automate the deployment of our code. Then, we'll turn to Android, where we'll get acquainted with the basics of Android development, building the task-tracking app that uses our AWS-based API. For the final week of the course, we'll use AWS to enhance our Android development, using Cognito for login and AWS Simple Notification Service to send notifications to our Android apps.

## Tools
* First class: javac/java. No IDE interestingness. Just the language.
* First 2 weeks: introduce Gradle/JUnit, run from the command line or IDE.
* Weeks 3/4: Gradle with IntelliJ. Run from the command line or IDE.
* Weeks 6-10: Android. Gradle with Android Studio. Run from IDE.
