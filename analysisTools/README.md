# analysisTools, a tool

## Download & Install

Clone project:
```
git clone https://github.com/btellstrom/analysisToolsKex
```

Installation:
```
mvn test
```

## Usage
```
java -jar target/tools-0.0.1-SNAPSHOT.jar
```

possible switches are:


Show all switches and their usage
```
--help
```


set the number of lists to be analysed (size of the experiment)
```
--size
```

set the length of the lists analysed
```
--length
```

set the output file
```
--file
```

set the type of output
```
--type
```
with possible arguments
```
returnTimes/rt, returnLists/rl, returnListTimes/all/rlt/rtl 
```
