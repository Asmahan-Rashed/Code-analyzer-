# Compiler_project


- You should have Java installed, We used Intelj to devolp this project and we used [Antlr4](https://jar-download.com/artifacts/org.antlr/antlr4-runtime?p=2) and [commons-io-2.11.0](https://commons.apache.org/proper/commons-io/download_io.cgi).

## How it works:

- Just input the file path for the code that needed to be analyzed, and you'll get a html page where the green sections indicate the sections that the code has entered and excuted safly, the pink sections are the sections that haven't been excuted and the orange color indicates branch coverage cases.

In this project, We use **Antlr4 program** to read *if statment* which takes each block and numbering it sequentially.
Once we put an input which contains **nested if** with *True* or *False* condition as input shown :

(You can find it in: [input.java](https://github.com/Asmahan-Rashed/Compiler_project/blob/main/input.java))

**INPUT**


![6644ab21-7a2b-4bc9-bb88-3bf5190dbe4b](https://user-images.githubusercontent.com/76706477/227800586-d27d8ef5-e7cf-41d1-88c4-44543e0415dd.jpg)


As Input shown, *IF* function contains another *IF* which have an *Print* function. Our Purpose is to put *\\blocknumber + "number of this block"* in each new statment, as shown in the output.


**OUTPUT**

![14b5866b-ca92-4a17-b4f5-a06a448523aa](https://user-images.githubusercontent.com/76706477/227800626-bab051a5-8b0b-4273-8d3c-f54e4798857e.jpg)



(You can find it in:[output.java](https://github.com/Asmahan-Rashed/Compiler_project/blob/main/output.java))

**To make this scenario:**

1- We took a grammer for if statment as you can find in ([JavaParser.g4](https://github.com/Asmahan-Rashed/Compiler_project/blob/main/JavaParser.g4))
with start rule:

![1cf18c5a-11f6-4f2d-b310-4a1aa0ae9501](https://user-images.githubusercontent.com/76706477/227800660-64117840-ecd7-4d4b-ac52-6fdf7daabc76.jpg)


2- After that we generate a parse tree, to show every branch of the tree and its children :


**Parsing Tree**

![83deae46-0e37-44eb-9b57-35fa0e718f94](https://user-images.githubusercontent.com/76706477/227800672-a5788346-a349-4a09-b465-f7adadd5a698.jpg)


3- By using *ListenerFunctions* for specefic tokens -as we need- We made a class *extends* from **Baselistener** which is:
[TestIf.java](https://github.com/Asmahan-Rashed/Compiler_project/blob/main/TestIf.java)

4- We output our output in new file which is:

(You can find it in:[output.java](https://github.com/Asmahan-Rashed/Compiler_project/blob/main/output.java))




***Some Tips***


In our main file:  [main.java](https://github.com/Asmahan-Rashed/Compiler_project/blob/main/main.java) We use:


1- We use **Walker** to know how to walk through the tree and point to specific position to write before or after this position:

![2023-03-22_18h36_13](https://user-images.githubusercontent.com/76706477/226975538-ef5dc1ce-e98e-4dfc-976d-ac19fc813df2.png)


2- We use **Rewritter** to write in specific position as we need:

![q2023-03-22_18h36_13](https://user-images.githubusercontent.com/76706477/226975441-aaeab462-af3a-4f10-b6af-1ac05a3688ac.png)

