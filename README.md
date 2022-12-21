# Not Pegasus

You are working for a small security firm that is helping TELLTHEM Communications understand user behaviour on the cellular phone network. Your goal is to build a tool that can perform basic analysis of call logs.

A `CallRecord` is a datatype that represents an individual cell phone call. It consists of a **from** phone number, a **to** phone number, the start time of the call and the duration of the call. The start time of a call is measured in seconds elapsed since January 1, 2020 (12:00 a.m.). The call duration is measured in seconds, too.

A call that begins at time 10034 (10034 seconds after January 1, 2020, 12:00 a.m.) and lasts 100 seconds is considered **active** in the closed interval [10034, 10133]. A call is considered active during a particular minute if it is active for at least one second of that minute.

You are implementing a datatype called `CallLogger` that can be used to track a set of calls.

The `CallRecord` type has been implemented for you and you should not change its implementation. Notice that all the fields in `CallRecord` are `public` and `final` and that allows you to access these fields without explicit `get` methods. The field types in `CallRecord` are all immutable and there are no worries of accidental mutation of a `CallRecord` instance.

**Start by looking at the provided tests first! If you are uncertain whether a test case is correct or not, focus on the specifications and implement to the specifications.**

## Tasks

The specifications for the methods you should implement are included in the skeleton code.

### Task 1: Preliminary Methods in `CallLogger`

You should implement:

1. the constructor
2. `add`
3. `getOriginatingCalls`
4. `getIncomingCalls`

### Task 2: Elementary Statistical Analysis

You should implement:

1. `getMostFrequentUser`
2. `getAvgCallDuration`
3. `getAvgCallDuration(String phoneNumber)`

### Task 3: Overlapping Calls

You should implement:

- `isOverLappingCall`

### Task 4: Peak Usage

You should implement:

- `getPeakUsage`

## Logistics

There is no partial credit for a task. You get credit for a task if your implementation passes all tests we subject your submission to. And there will be more tests than what  you will be provided with. The PPT has been broken up into different  tasks so that you can get partial credit. 

You should complete Tasks 1 and 2 in that order. You will not get any credit if you have a working implementation for Task 2 but not for Task 1.

**Grading**

| Work Accomplished | Grade |
| ----------------- | ----- |
| Task 1 does not pass all hidden tests | F |
| Task 1 passes all hidden tests | C |
| Tasks 1, 2 pass all hidden tests | B |
| Tasks 1, 2 and one of Tasks 3, 4 pass all hidden tests | A |
| Tasks 1 through 4 pass all hidden tests | A+ |

### Submission Instructions

+ Submit your work to the Github classroom repository that was created for you.
+ **Do not alter the directory/folder structure. You should retain the structure as in this repository.**
+ Do not wait until the last minute to push your work to Github. It is a good idea to push your work at intermediate points as well. _We would recommend that you get your Git and Github workflow set up at the start._

### What Should You Implement / Guidelines

+ You should implement all the methods that are indicated with `TODO`.
+ Passing the provided tests is the minimum requirement. Use the tests to identify cases that need to be handled. Passing the provided tests is *not sufficient* to infer that your implementation is complete and that you will get full credit. Additional tests will be used to evaluate your work. The provided tests are to guide you.
+ You can implement additional helper methods if you need to but you should keep these methods `private` to the appropriate classes.
+ You **may** implement additional `class`es as helpers.
+ You can use additional **standard** Java libraries by importing them. You should not use **any other** libraries.
+ Do not throw new exceptions unless the specification for the method permits exceptions.


## Honour Code

By submitting your work to GitHub you agree to the following:

+ You did not consult with any other person in completing this activity.
+ You did not aid any other person in the class in completing their activity.
+ If you consulted any external sources, such as resources available on the World Wide Web, in completing the examination then you have cited the source. (You do not need to cite class notes or Sun/Oracle Java documentation.)
+ You are not aware of any infractions of the honour code for this examination.

## Answers to FAQs

* **Can I consult Java documentation and other Internet-based sources?**

    Yes, you can. The point of this test is not to demonstrate mastery over syntax but that you can solve a problem in a    reasonable amount of time with reasonable resources.

    *If you find useful information online outside the official Java documentation and the course material, you must cite the source. You should do so by adding comments in your source code.*

    Naturally you are expected to adhere to all of the course and UBC policies on academic integrity.

* **Isn't 75 minutes insufficient time to produce a working implementation?**

    The questions are straightforward, and these are not very different from what one might sometimes encounter on a job interview (for example). The difference is that you get less time during an interview (10-15 minutes) with no access to additional resources. The time allotted is reasonable in that regard and I am expecting that everyone will be able to clear this bar. The goal is that it is possible to say, at a minimal level, what everyone who completes this course can achieve.

* **Why am I not guaranteed full credit if my implementation passes all the provided tests?**

    It is easy to develop an implementation that passes the provided tests and not much else. A good-faith implementation that passes all the provided tests is very likely to pass other tests too.