# Compiler_project


- You should have Java installed, We used Intelj to devolp this project and we used [Antlr4](https://jar-download.com/artifacts/org.antlr/antlr4-runtime?p=2) and [commons-io-2.11.0](https://commons.apache.org/proper/commons-io/download_io.cgi).

## How it works:

Just input the path for the Java code that needed to be analyzed, and you'll get a html page and a text file where you can find the follwing:

- For every block that's excted we write the no.of the block into the output.text file.
- If the HTML page, the excuted code will be colered in green and the blocks that weren't excuted (uncalled function for example) will be in pink.
- If there were a branch coverage case it will be colored orange in the HTML page.

You can see an example in the images bellow bellow:

**INPUT**


![6644ab21-7a2b-4bc9-bb88-3bf5190dbe4b](https://github.com/Aml-Hassan-Abd-El-hamid/Machine-learning-college-course/blob/main/Screenshot%20(37).png)


As Input shown, *IF* function contains another *IF* which have an *Print* function. Our Purpose is to put *\\blocknumber + "number of this block"* in each new statment, as shown in the output.


**OUTPUT**

![14b5866b-ca92-4a17-b4f5-a06a448523aa](https://github.com/Aml-Hassan-Abd-El-hamid/Machine-learning-college-course/blob/main/Screenshot%20(38).png)


In our main file:  [main.java](https://github.com/Asmahan-Rashed/Compiler_project/blob/main/main.java) We use:


1- We use **Walker** to know how to walk through the tree and point to specific position to write before or after this position:

![2023-03-22_18h36_13](https://user-images.githubusercontent.com/76706477/226975538-ef5dc1ce-e98e-4dfc-976d-ac19fc813df2.png)


2- We use 2 **Rewritter** to write in the out.java and new.html, the out.java is a augmented code that writes the style.css, which adds the right colors to the HTML page that shows as an output of our program.

3- we run the out.java directly from our main, and out.java directly run the HTML page on the default browser of your device.
