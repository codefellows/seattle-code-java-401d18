# Code 401: Java Facilitator Guide

Welcome to the guide repo for Code 401: Java! This document will give you an overview of the course and resources you might need.

## Resources

- [This guide](./) contains most of the pieces of curriculum you need to run the course, mainly in the `curriculum` directory. In each day's directory, you can find the readings due on that day, the README that contains an agenda for lecture on that day, and the lab assignment to be done after that lecture.
- [The Prework Guide Folder](https://github.com/codefellows/code-401-java-guide/curriculum/prework) contains the prework that students must complete before beginning the course. It's iframed into Canvas, so you shouldn't need to share that link directly with students unless they're having trouble accessing Canvas.
- [The Common Curriculum guide repo](https://github.com/codefellows/common_curriculum) contains the data structures & algorithms content, including readings, lecture content, and lab content for DS&A labs (for each lecture/lab pair divisible by 5). It also contains the daily code challenges.
  - It also contains the career coaching content, common prework pieces, and the like.
- [The Canvas template](https://canvas.instructure.com/courses/1453652) can be imported, along with the [common curriculum Canvas template](https://canvas.instructure.com/courses/1294324), to create a Canvas course that contains pre-built assignments (labs/readings/code challenges/career coaching/everything).
- [The Code Challenges repo](https://github.com/codefellows/code-challenges) is an ad-hoc repo that contains assorted code challenges, used for final whiteboard interviews and whiteboarding in front of the class. Note that it's not a "gold-standard" repo, so you should take everything in there with a grain of salt.

## Files in The Curriculum

Within each day's directory:

- `README.md` files are the student-facing daily agenda.
- `DISCUSSION.md` files are the student-facing daily readings. Each day's directory contains the readings due on that day (so that thematically grouped reading/lecture/lab materials are all in one directory). These are iframed into Canvas, so you shouldn't need to touch these files.
- `LAB.md` files are the student-facing daily lab assignments. These are also iframed into Canvas, and shouldn't need to be touched. They also contain a rubric for grading that lab; this ensures that students and TAs have visibility into how to grade.
- `FACILITATOR.md` files contain secret information for you, the instructor. They should NOT be shared with students.

There are also a collection of other student-facing files within the `curriculum` directory:

- `projects/` contains two types of documents for each of midterm & final projects: a Topics document, which helps students know what sort of projects will be feasible when they start coming up with ideas two weeks before projects begin; and a Requirements document, which lists explicitly what the requirements for their projects are. These are both to be shared with students at an appropriate time. (I generally share the "topics" doc during class 10/35, the Friday before they start working with project groups; and then the "requirements" doc during class 20/45, the Friday before project week.)
- `GradleWorkflow.md` and `SpringAuthCheatSheet.md` are walkthrough/cheat-sheet/checklist type documents that students should use for those purposes. These docs should be shared on day 2/day 15 of class. I keep them at the root level so that students can find them easily, wihtout hunting through individual days' directories.
- `CodeLabs_GradingRubrics.md` contains grading rubrics for the common curriculum DS&A labs.

Make sure to also look at the resources in the [Common Curriculum repo](https://github.com/codefellows/common_curriculum) for more student-facing content and facilitator guides on whiteboarding, pseudocode, and code challenges.

## Lecture Structure

In general, we allot 3 hours for lecture. Those three hours tend to break down as follows:

- 1 hour for code review of the previous day's lab. This can include the code challenge or not, as appropriate. Reviewing the lab most commonly looks like opening one student's submission, running it, and walking through the code as a group, asking students (in-turn, or another bias-free selection method) to explain particular portions of the code and how they fit together. If you run lecture with a Why > What > How structure, you may enjoy running code review with a How > What > Why format. See [Instructor Skills](https://drive.google.com/a/codefellows.com/file/d/1F4Ei8-Qe10V5t5bs5kRa3Z5Zoi2_1dKy/view?usp=sharing).

But, code review can also go in other ways; sometimes I print student code out on paper, pair up students, and ask them to do a line-by-line review of each others' code. Or, I'll show two submissions for that day, and ask students to debate which one is "better". Basically, whatever you think is most effective here, go for it.

- 1 hour for introduction of new concepts. Most commonly, this is a mixed-lecture format. I tend to structure this hour to start with warmup questions that build on prior knowledge or the reading assignment, and make students answer these questions in pairs. I then build off of their responses to those questions to build out the set of topics that I cover, doing mostly whiteboarding during this portion of lecture. Other instructors like using slides, or scrolling through a GitHub README document. This portion of the class also often includes student exercises, where they work in small groups to answer questions or come up with theories about how a new piece of technology will work.
- 1 hour for lab intro. This is the portion of the day when I demo any new technologies learned while live-coding in front of the class. I try to keep this as un-scripted as possible, so that students can see my natural debugging process; and I try to incorporate student proposals/ideas as much as possible during our code example. This is the part where they get to see that it's okay to try things in the code, even if you're not sure they will work! And I try to use the documentation, or StackOverflow, or whatever resources I'd like for them to use as they use that same piece of technology during lab. I also try to implement something fairly different from the lab itself; this is 401, and I expect people to be able to look at a code example with a very different goal and find the pieces that best apply to the code they need to write.

## Timeline/Checklist

### One month before the first day of class

[ ] Start to read through the curriculum/list of topics.
[ ] Practice with any technologies that are new to you (preferably by doing the lab assignments).
[ ] If necessary, start looking for TAs.

### Two weeks before

[ ] Access your Canvas course instance & verify the import of the common curriculum & Java templates. Adjust due dates/times as necessary to match your schedule. Bemoan Canvas's lack of batch operations.
[ ] Create a course repo.
[ ] Consider the first day of class. Ensure you have an orientation planned, and consider how you'll set your class culture.

### One week before

[ ] Confirm students have been added to Canvas.
[ ] Send email to students reminding them to do the prework, and communicating where to be & when on the first day.
[ ] Finalize TA list.
[ ] Practice your lectures for the first week (as necessary).

### The Friday/weekend before

[ ] Confirm students have been added to Slack.
[ ] Set up a meeting with the TAs for, at latest, the first day of class.
[ ] If possible, practice at least one lecture in the space where you'll be teaching.
[ ] Meet with TAs.
    [ ] Get to know them, make them feel comfortable, etc.
    [ ] Ensure they have access to the curriculum.
    [ ] Confirm grading standards/timelines.
    [ ] Remind them to escalate to you constantly.

### Each day during the course

[ ] Add the next day's agenda/README file to the course repo in an appropriately named directory.
[ ] Add demo code worked on in class to the course repo in the appropriate directory.
[ ] Practice lecture/demo for the following class.
