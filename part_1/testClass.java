public class testClass {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1 || index >= input.length() - 3) {
                break;
            }
            System.out.println(index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
        }
    }
    public void test() {
        String string = "abcabcabcabca";
        findAbc(string);
    }

    public static void main(String[] args) {
        testClass tester = new testClass();
        tester.test();
    }
}