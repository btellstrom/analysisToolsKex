import numpy as np

class config:

    def __init__(self, fileName):
        self.nrOfMeasurements = []
        self.listLength = []
        self.nrOfSorts = []
        self.nrOfLists = []
        self.sortingAlgorithms = []
        self.fileFamily = ""
        file = open(fileName,'r')
        self.readConfig(file)

    def readConfig(self, file):
        #TODO, loop over option name rather than line?
        for line in file:
            line.rstrip()
            if "nrOfLists" in line:
                self.nrOfLists = int(line[len("nrOfLists")+1:])

            elif "fileFamily" in line:
                self.fileFamily = line[len("fileFamily")+1:-1]

            optionName = line.split("=", 1)[0]
            self.loopOverOption(optionName, line)


    def loopOverOption(self, optionName, line):
        index = 0
        for string in line[len(optionName)+1:-2].split(","):
            if (optionName == "listLength"):
                self.listLength.append(int(string))
            elif (optionName == "nrOfSorts"):
                self.nrOfSorts.append(int(string))
            elif (optionName == "sortingAlgorithms"):
                self.sortingAlgorithms.append(string)
            elif (optionName == "nrOfMeasurements"):
                self.nrOfMeasurements.append(string)

            index += 1

    def returnVariancePercentTotal(self, fileName, nrOfSorts):
        relativeVariance = calculatePercentualVariance(fileName)
        totalVariance = calculateTotalVariance(fileName)
        relativeDivided =  calculatePercentualVarianceDividedByNrOfSorts(fileName, nrOfSorts)
        totalDivided = calculateTotalVarianceDividedByNrOfSorts(fileName, nrOfSorts)
        return relativeVariance, totalVariance, relativeDivided, totalDivided

    def calculateVarianceToFile(self):
        #order: nrOfMeasurements, sortingAlgorithms, listLength, nrOfSorts
        #name = fileFamily + "Length" + listLength + sortingAlgorithm + nrOfSorts + "SortsTimes.csv"

        for nrOfMeasurements in self.nrOfMeasurements:
            for sortingAlgorithm in self.sortingAlgorithms:
                totalVarianceFile = "totalVarianceFor" + sortingAlgorithm + "Initial.csv"
                relativeVarianceFile = "relativeVarianceFor" + sortingAlgorithm + "Initial.csv"
                totalVarianceDividedFile = "totalVarianceDividedFor" + sortingAlgorithm + "Initial.csv"
                relativeVarianceDividedFile = "relativeVarianceDividedFor" + sortingAlgorithm + "Initial.csv"
                
                row = 0
                column = 0
                totalVariance = [0]*len(self.listLength)
                for i in range(0, len(totalVariance)):
                    totalVariance[i] = [0]*len(self.nrOfSorts)

                relativeVariance = [0]*len(self.listLength)
                for i in range(0, len(relativeVariance)):
                    relativeVariance[i] = [0]*len(self.nrOfSorts)

                totalVarianceDivided = [0]*len(self.listLength)
                for i in range(0, len(totalVarianceDivided)):
                    totalVarianceDivided[i] = [0]*len(self.nrOfSorts)

                
                relativeVarianceDivided = [0]*len(self.listLength)
                for i in range(0, len(totalVarianceDivided)):
                    relativeVarianceDivided[i] = [0]*len(self.nrOfSorts)
                    
                for listLength in self.listLength:
                    for nrOfSorts in self.nrOfSorts:
                        fileName = self.fileFamily + "Length" + str(listLength) + str(sortingAlgorithm) + str(nrOfSorts) + "SortsTimes.csv"
                        try:
                            variance = self.returnVariancePercentTotal(fileName, nrOfSorts)
                            totalVariance[row][column] = variance[1]
                            relativeVariance[row][column] = variance[0]
                            totalVarianceDivided[row][column] = variance[3]
                            relativeVarianceDivided[row][column] = variance[2]
                            column += 1
                        except:
                            pass
                    row += 1
                    column = 0

                self.printToFile(totalVariance, totalVarianceFile)
                self.printToFile(relativeVariance, relativeVarianceFile)
                self.printToFile(totalVarianceDivided, totalVarianceDividedFile)
                self.printToFile(relativeVarianceDivided, relativeVarianceDividedFile)


    def printToFile(self, variance, fileName):
        file = open(fileName, 'w')
        height = len(self.listLength)
        width = len(self.nrOfSorts)
        file.write("Length of List, Number Of Sorts, Variance\n")
        for i in range(0, height):
            for j in range(0, width):
                file.write(str(self.listLength[i]) + ",")
                file.write(str(self.nrOfSorts[j]) + ",")
                file.write(str(variance[i][j]))
                file.write("\n")
        file.close()


def calculatePercentualVariance(fileName):
    file = open(fileName, "r")

    times = []
    for line in file:
        times.append(int(line))

    mean = np.mean(times)
    for i in range(0, len(times)):
        times[i] = (times[i]-mean)/mean
        
    variance = np.var(times)
    return variance

def calculateTotalVariance(fileName):
    file = open(fileName, "r")
    times = []
    for line in file:
        times.append(int(line))
    variance = np.var(times)
        
    return variance

def calculatePercentualVarianceDividedByNrOfSorts(fileName, nrOfSorts):
    file = open(fileName, "r")

    times = []
    for line in file:
        times.append(int(line)/float(nrOfSorts))

    mean = np.mean(times)
    for i in range(0, len(times)):
        times[i] = (times[i]-mean)/mean
        
    variance = np.var(times)
    return variance

def calculateTotalVarianceDividedByNrOfSorts(fileName, nrOfSorts):
    file = open(fileName, "r")
    times = []
    for line in file:
        times.append(int(line)/float(nrOfSorts))
    variance = np.var(times)
        
    return variance
    

def main():
    configFile = "generatedConfig.cfg"
    
    cofiguration = config(configFile)

    cofiguration.calculateVarianceToFile()

main()
