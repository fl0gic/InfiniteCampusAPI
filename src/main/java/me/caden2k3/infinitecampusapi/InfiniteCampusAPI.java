package me.caden2k3.infinitecampusapi;

import lombok.Getter;

import java.io.*;

public class InfiniteCampusAPI {
    private static PrintWriter out;

    @Getter private InfiniteCampusAPI instance;

    public InfiniteCampusAPI() {
        instance = this;
    }

    public static void main(String[] args) throws Exception {
        InfiniteCampusAPI main = new InfiniteCampusAPI();
        File f = new File("grades.txt");
        if (f.exists())
            f.delete();

        try {
            out = new PrintWriter(new BufferedWriter(new FileWriter("grades.txt")));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Please enter your district code:");
        String districtCode = main.getInput();

        InfiniteCampus core = new InfiniteCampus(districtCode);
        print("Found District Information:");
        print("District: " + core.getDistrictInfo().getDistrictName());
        print("State: " + core.getDistrictInfo().getStateCode());
        print("Base URL: " + core.getDistrictInfo().getDistrictBaseURL());
        print("District App Name: " + core.getDistrictInfo().getDistrictAppName());

        print("Attempting login...");
        System.out.println("Username: ");
        String username = main.getInput();

        System.out.println("Password: ");
        String passwordString = main.getInput();
        System.out.println(passwordString);

        print("Logging into user " + username + "...");
        boolean successfulLogin = core.checkCredentials(username, passwordString);
        print(successfulLogin ? "Login success!" : "Login failed!");
        if (!successfulLogin) {
            print("\nPress any key to exit...");
            System.in.read();
            return;
        }
        print("\n");

        Student student = new Student(username, passwordString, core);
        print(student.getInfoString());

        print("\n");

        print(student.getClassbookManager().getInfoString());

        out.close();

        print("\nUser info dump successful!\nPress any key to exit...");
        System.in.read();
    }

    public static void print(String s) {
        System.out.println(s);
        out.println(s);
    }

    private String getInput() {
        String inputString = "";
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            inputString = bufferRead.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return inputString;
    }
}
