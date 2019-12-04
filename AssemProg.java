import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * AssemProg is a class that reads a text file that translates
 * ARM assembly code into machine language (1's and 0's)
 * 
 * @author Rachael Mahar
 * @version due reading day
 */

public class AssemProg {
    public static void main(String[] args) throws IOException {
        // creates scanner
        Scanner keyboard = new Scanner(System.in);
        // asks for input file from user
        System.out.println("Please specify input file: ");
        String inFile = keyboard.next();
        Scanner input = new Scanner(new File(inFile));
        Scanner input2 = new Scanner(new File(inFile));
        // asks for output file from user
        System.out.println("Please specify output file: ");
        String outFile = keyboard.next();
        PrintWriter output = new PrintWriter(new File(outFile));

        // create an array that will hold labels
        ArrayList<String> items = new ArrayList<String>();
        // the first time reading a file in, it checks to see if the string contains a :
        // which will add to the array "items"
        // this is useful later on in branches so you can see what line a label is from
        while (input.hasNextLine()) {
            String s = input.next();
            if (s.contains(":")) {
                String sub = s.substring(0, s.length()-1);
                items.add(sub);
            }
            else {
                items.add("");
            }
            if (input.hasNextLine()){
                input.nextLine();
            }
        }

        // will keep track of lines
        int count = 1;
        // while the file has a next line...
        while (input2.hasNextLine()) {
            // if there are blank lines, go to next one
            if (!input2.hasNextLine()) {
                input2.nextLine();
            }
            // s will be the first line of every code
            String s = input2.next();
            // if s is ; then skip the rest of the line and starts the while loop over
            if (s.equals(";")) {
                input2.nextLine();
                continue;
            }
            // if s contains : (a label), you start the while loop over again
            if (s.contains(":")) {
                continue;
            }
            // s will be checked against each word in quotes and prints out the corresponding opcode
            // then saves next 3 values in strings that will then be passed to the correct format method and 
            // returns a string to print out, it will also increment count to keep track of line number
            else if (s.equals("ADD")) {
                output.print("10001011000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            else if (s.equals("ADDI")) {
                output.print("1001000100 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatI(a,b,c));
                count++;
            }
            else if (s.equals("ADDIS")) {
                output.print("1011000100 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatI(a,b,c));
                count++;
            }
            else if (s.equals("ADDS")) {
                output.print("10101011000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            else if (s.equals("AND")) {
                output.print("10001010000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            else if (s.equals("ANDI")) {
                output.print("1001001000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatI(a,b,c));
                count++;
            }
            else if (s.equals("ANDIS")) {
                output.print("1111001000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatI(a,b,c));
                count++;
            }
            else if (s.equals("ANDS")) {
                output.print("11101010000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            // branch instructions print out the opcode, keep track of next string
            // then checks to see if next string is in items array and prints out the binary
            // increments count
            else if (s.equals("B")) {
                output.print("000101 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin26(i, count));
                    }
                }
                count++;
            }
            else if (s.equals("B.EQ")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 0000");
                count++;
            }
            else if (s.equals("B.NE")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 0001");
                count++;
            }
            else if (s.equals("B.HS")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 0010");
                count++;
            }
            else if (s.equals("B.LO")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 0011");
                count++;
            }
            else if (s.equals("B.MI")) { 
                output.print("01010100 ");
                String a = input2.next();
                // get rid of comments
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 0100");
                count++;
            }
            else if (s.equals("B.PL")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 0101");
                count++;
            }
            else if (s.equals("B.VS")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 0110");
                count++;
            }
            else if (s.equals("B.VC")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 0111");
                count++;
            }
            else if (s.equals("B.HI")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 1000");
                count++;
            }
            else if (s.equals("B.LS")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 1001");
                count++;
            }
            else if (s.equals("B.GE")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 1010");
                count++;
            }
            else if (s.equals("B.LT")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 1011");
                count++;
            }
            else if (s.equals("B.GT")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 1100");
                count++;
            }
            else if (s.equals("B.LE")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 1101");
                count++;
            }
            else if (s.equals("B.AL")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 1110");
                count++;
            }
            else if (s.equals("B.NV")) { 
                output.print("01010100 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin19(i, count));
                    }
                }
                output.print(" 0 1111");
                count++;
            }
            else if (s.equals("BL")) {
                output.print("100101 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (a.equals(items.get(i))) {
                        output.print(getBin26(i, count));
                    }
                }
                count++;
            }
            else if (s.equals("BR")) {
                output.print("11010110000 ");
                String a = input2.next();
                if (a.contains(";")) {
                    a = a.substring(0,a.indexOf(";"));
                }
                a = a.substring(1, a.length());
                int Rn = Integer.parseInt(a);
                output.print("00000 000000 " + getBin5(Rn) + " 00000");
                count++;
            }
            else if (s.equals("CBNZ")) {
                output.print("10110101 ");
                String a = input2.next();
                String b = input2.next();
                if (b.contains(";")) {
                    b = b.substring(0,b.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (b.equals(items.get(i))) {
                        output.print(formatCB(a, count+1)); 
                    }
                }
                count++;
            }
            else if (s.equals("CBZ")) {
                output.print("10110100 ");
                String a = input2.next();
                String b = input2.next();
                if (b.contains(";")) {
                    b = b.substring(0,b.indexOf(";"));
                }
                for (int i=0; i<items.size(); i++) {
                    if (b.equals(items.get(i))) {
                        output.print(formatCB(a, i-1));
                    }
                }
                count++;
            }
            else if (s.equals("EOR")) {
                output.print("11001010000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            else if (s.equals("EORI")) {
                output.print("1101001000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatI(a,b,c));
                count++;
            }
            else if (s.equals("LDUR")) {
                output.print("11111000010 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatD(a,b,c));
                count++;
            }
            else if (s.equals("LSL")) {
                output.print("11010011011 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatRShamt(a,b,c));
                count++;
            }
            else if (s.equals("LSR")) {
                output.print("11010011010 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatRShamt(a,b,c));
                count++;
            }
            else if (s.equals("ASR")) {
                output.print("10010011010 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatRShamt(a,b,c));
                count++;
            }
            else if (s.equals("ORR")) {
                output.print("10101010000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            else if (s.equals("ORRI")) {
                output.print("1011001000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatI(a,b,c));
                count++;
            }
            else if (s.equals("STUR")) {
                output.print("11111000000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatD(a,b,c));
                count++;
            }
            else if (s.equals("SUB")) {
                output.print("11001011000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            else if (s.equals("SUBI")) {
                output.print("1101000100 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatI(a,b,c));
                count++;
            }
            else if (s.equals("SUBIS")) {
                output.print("1111000100 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatI(a,b,c));
                count++;
            }
            else if (s.equals("SUBS")) {
                output.print("11101011000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            // Simple pseudoinstructions
            else if (s.equals("CMP")) {
                output.print("11101011000 ");
                String a = input2.next();
                String b = input2.next();
                String c = "XZR";
                output.print(formatR(c,a,b));
                count++;
            }
            else if (s.equals("CMPI")) {
                output.print("1111000100 ");
                String a = input2.next();
                String b = input2.next();
                String c = "XZR";
                output.print(formatI(c,a,b));
                count++;
            }
            else if (s.equals("MOV")) {
                output.print("10001011000 ");
                String a = input2.next();
                String b = input2.next();
                if (b.contains(";")) {
                    b = b.substring(0,b.indexOf(";"));
                }
                b = b + ",";
                String c = "XZR";
                output.print(formatR(a,b,c));
                count++;
            }
            else if (s.contains("NOP")) { // (do nothing)
                output.print("000101 00000000000000000000000000");
                count++;
            }
            else if (s.equals("NEG")) { // (negative)
                output.print("11001011000 ");
                String a = input2.next();
                String b = input2.next();
                if (b.contains(";")) {
                    b = b.substring(0,b.indexOf(";"));
                }
                String c = "XZR";
                output.print(formatR(a,c,b));
                count++;
            }
            else if (s.equals("RET")) {
                output.print("11010110000 00000 000000 11110 00000");
                count++;
            }
            // Additional instructions
            else if (s.equals("MOVK")) {
                output.print("111100101 ");
                count++;
            }
            else if (s.equals("MOVZ")) {
                output.print("110100101 ");
                count++;
            }
            else if (s.equals("MUL")) {
                output.print("10011011000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            else if (s.equals("SDIV")) {
                output.print("10011010110 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            else if (s.equals("SMULH")) {
                output.print("10011011010 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            else if (s.equals("UDIV")) {
                output.print("10011011000 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            else if (s.equals("UMULH")) {
                output.print("10011011110 ");
                String a = input2.next();
                String b = input2.next();
                String c = input2.next();
                output.print(formatR(a,b,c));
                count++;
            }
            else if (s.equals("I")) {
                count++;
            }
            output.println();
            output.print(count + "      ");
            if(input2.hasNextLine()) {
                input2.nextLine();
            }
        }
        // closes files
        input.close();
        output.close();
    }
    // R format has 3 parameters from input file and returns binary string
    public static String formatR(String a, String b, String c) {
        // gets rid of ,
        String subA = a.substring(1, a.length()-1);
        String subB = b.substring(1, b.length()-1);
        // gets rid of comments after 3rd string
        if (c.contains(";")) {
            c = c.substring(0,c.indexOf(";"));
        }
        String subC = c.substring(1, c.length());
        // finds correct register if there is a stack pointer or XZR
        if (subA.equals("P")) {
            subA = "28";
        }
        if (subB.equals("P")) {
            subB = "28";
        }
        if (subC.equals("P")) {
            subC = "28";
        }
        if (subA.equals("ZR") || subA.equals("Z")) {
            subA = "31";
        }
        if (subB.equals("ZR") || subB.equals("Z")) {
            subB = "31";
        }
        if (subC.equals("ZR") || subC.equals("Z")) {
            subC = "31";
        }
        // converts strings to intergers
        int Rd = Integer.parseInt(subA);
        int Rn = Integer.parseInt(subB);
        int Rm = Integer.parseInt(subC);
        // passes numbers to methods that return binary and add that to a string to return it
        String s = getBin5(Rm) + " " + "000000" + " " + getBin5(Rn) + " " + getBin5(Rd);
        return s;
    }
    // R format (with shamt) has 3 parameters from input file and returns binary string
    public static String formatRShamt(String a, String b, String c) {
        // gets rid of ,
        String subA = a.substring(1, a.length()-1);
        String subB = b.substring(1, b.length()-1);
        // gets rid of comments after 3rd string
        if (c.contains(";")) {
            c = c.substring(0,c.indexOf(";"));
        }
        String subC = c.substring(1, c.length());
        // finds correct register if there is a XZR
        if (subA.equals("ZR")) {
            subA = "0";
        }
        if (subB.equals("ZR")) {
            subB = "0";
        }
        if (subC.equals("ZR")) {
            subC = "0";
        }
        // converts strings to intergers
        int Rd = Integer.parseInt(subA);
        int Rn = Integer.parseInt(subB);
        int shamt = Integer.parseInt(subC);
        // passes numbers to methods that return binary and add that to a string to return it
        String s = "00000" + " " + getBin6(shamt) + " " + getBin5(Rn) + " " + getBin5(Rd);
        return s;
    }
    // I format has 3 parameters from input file and returns binary string
    public static String formatI(String a, String b, String c) {
        // gets rid of ,
        String subA = a.substring(1, a.length()-1);
        String subB = b.substring(1, b.length()-1);
        // gets rid of comments after 3rd string
        if (c.contains(";")) {
            c = c.substring(0,c.indexOf(";"));
        }
        String subC = c.substring(1, c.length());
        // finds correct register if there is a link register, stack pointer or XZR
        if (subA.equals("ZR") || subA.equals("Z")) {
            subA = "31";
        }
        if (subA.equals("R")) {
            subA = "30";
        }
        if (subB.equals("ZR") || subB.equals("Z")) {
            subB = "31";
        }
        if (subC.equals("ZR") || subC.equals("Z")) {
            subC = "31";
        }
        if (subA.equals("P")) {
            subA = "28";
        }
        if (subB.equals("P")) {
            subB = "28";
        }
        // converts strings to intergers
        int Rd = Integer.parseInt(subA);
        int Rn = Integer.parseInt(subB);
        int ALU = Integer.parseInt(subC);
        // passes numbers to methods that return binary and add that to a string to return it
        String s = getBin12(ALU) + " " + getBin5(Rn) + " " + getBin5(Rd);
        return s;
    }
    // D format has 3 parameters from input file and returns binary string
    public static String formatD(String a, String b, String c) {
        // gets rid of ,
        String subA = a.substring(1, a.length()-1);
        String subB = b.substring(2, b.length()-1);
        // gets rid of comments after 3rd string
        if (c.contains(";")) {
            c = c.substring(0,c.indexOf(";"));
        }
        String subC = c.substring(1, c.length()-1);
        // finds correct register if there is a link register, stack pointer or XZR
        if (subA.equals("P")) {
            subA = "28";
        }
        if (subA.equals("R")) {
            subA = "30";
        }
        if (subB.equals("P")) {
            subB = "28";
        }
        if (subB.equals("R")) {
            subB = "30";
        }
        if (subA.equals("ZR")) {
            subA = "";
        }
        if (subB.equals("ZR")) {
            subB = "0";
        }
        if (subC.equals("ZR")) {
            subC = "0";
        }
        // converts strings to intergers
        int Rt = Integer.parseInt(subA);
        int Rn = Integer.parseInt(subB);
        int DT = Integer.parseInt(subC);
        // passes numbers to methods that return binary and add that to a string to return it
        String s = getBin9(DT) + " 00 " + getBin5(Rn) + " " + getBin5(Rt);
        return s;
    }
    // B format has 3 parameters from input file and returns binary string
    public static String formatB(String a, String b, String c) {
        // gets rid of ,
        String subA = a.substring(1, a.length()-1);
        String subB = b.substring(1, b.length()-1);
        // gets rid of comments after 3rd string
        if (c.contains(";")) {
            c = c.substring(0,c.indexOf(";"));
        }
        String subC = c.substring(1, c.length());
        // finds correct register if there is a XZR
        if (subA.equals("ZR")) {
            subA = "31";
        }
        if (subB.equals("ZR")) {
            subB = "31";
        }
        if (subC.equals("ZR")) {
            subC = "31";
        }
        // converts strings to intergers
        int Rd = Integer.parseInt(subA);
        int Rn = Integer.parseInt(subB);
        int Rm = Integer.parseInt(subC);
        // passes numbers to methods that return binary and add that to a string to return it
        String s = getBin5(Rm) + " " + "000000" + " " + getBin5(Rn) + " " + getBin5(Rd);
        return s;
    }
    // CB format has 3 parameters from input file and returns binary string
    public static String formatCB(String a, int line) {
        String subA = "";
        // gets rid of comments
        if (a.contains(";")) {
            a = a.substring(0,a.indexOf(";"));
        }
        // finds correct register if there is a stack pointer
        if (a.equals("SP,")) {
            subA = "28";
        }
        else {
            subA = a.substring(1, a.length()-1);
        }
        // converts strings to intergers
        int Rt = Integer.parseInt(subA);
        // passes numbers to methods that return binary and add that to a string to return it
        return getBin19(line, Rt) + " " + getBin5(Rt);
    }
    // converts number to binary and returns 5 places
    public static String getBin5(int i) {
        String num = Integer.toBinaryString(i);
        num = "00000000" + num;
        String sub = num.substring(num.length()-5, num.length());
        return sub;
    }
    // converts number to binary and returns 6 places
    public static String getBin6(int i) {
        String num = Integer.toBinaryString(i);
        num = "00000000" + num;
        String sub = num.substring(num.length()-6, num.length());
        return sub;
    }
    // converts number to binary and returns 9 places
    public static String getBin9(int i) {
        String num = Integer.toBinaryString(i);
        num = "00000000000000000000" + num;
        String sub = num.substring(num.length()-9, num.length());
        return sub;
    }
    // converts number to binary and returns 12 places
    public static String getBin12(int i) {
        String num = Integer.toBinaryString(i);
        num = "00000000000000000" + num;
        String sub = num.substring(num.length()-12, num.length());
        return sub;
    }
    // takes 2 numbers, the number a label is at and the line number you are working with
    public static String getBin19(int i, int count) {
        String n = "";
        // num is the distance between the current line and where label is
        int num = i - count;
        // if count is smaller, num is a positive binary number, change to binary and add 0's
        if (count < i) {
            n = Integer.toBinaryString(num);
            n = "00000000000000000000000000000000000" + n;
        }
        // if count is bigger, num is a negative binary number, change to binary and add 1's
        if (count > i) {
            n = Integer.toBinaryString(num);
            n = "1111111111111111111111111111111111" + n;
        }
        // make sure string is only 19 places
        n = n.substring(n.length()-19, n.length());
        return n;
    }
    // converts number to binary and returns 26 places
    public static String getBin26(int i, int lineNum) {
        String n = "";
        // num is the distance between the current line and where label is
        int num = i - lineNum;
        // if lineNum is smaller, num is a positive binary number, change to binary and add 0's
        if (lineNum < i) {
            n = Integer.toBinaryString(num);
            n = "00000000000000000000000000000000000" + n;
        }
        // if lineNum is bigger, num is a negative binary number, change to binary and add 1's
        if (lineNum > i) {
            n = Integer.toBinaryString(num);
            n = "1111111111111111111111111111111111" + n;
        }
        // make sure string is only 19 places
        n = n.substring(n.length()-26, n.length());
        return n;
    }
}