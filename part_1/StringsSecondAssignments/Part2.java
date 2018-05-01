public class Part2 {

    public int howMany(String stringa, String stringb) {
        int res = 0;
        int startIdx = stringb.indexOf(stringa);
        // not found
        if (startIdx == -1) {
            return res;
        }
        // found at least one
        while (true) {
            int stopIdx = stringb.indexOf(stringa, startIdx);
            if (stopIdx == -1) {
                break;
            }
            res++;
            startIdx = stopIdx + stringa.length();
        }
        return res;
    }

    public static void main(String[] args) {
        //tester
        Part2 tester = new Part2();
        String stringa = "AA";
        String stringb = "ATAAAA";
        int res = tester.howMany(stringa, stringb);
        System.out.println(res);
    }
}