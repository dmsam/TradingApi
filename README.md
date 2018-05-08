This is a sale processing API which reads input sale messages from a csv file and prints the sales report both to the console and to output csv files
Input csv file is in folder-src/main/resources/tradefile/input/
Ouput files are written both to console and to csv files in folder src/main/resources/tradefile/output.
The report is generated for every 10th message and at 50th message it will stop accepting messages and an adjustment report will be generated.
