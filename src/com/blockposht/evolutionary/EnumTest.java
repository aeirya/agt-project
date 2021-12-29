package com.blockposht.evolutionary;

public class EnumTest {
    
    enum SampleEnum {
        AEIRYA, TAHA
    }

    public static void main(String[] args) {
        SampleEnum e = SampleEnum.AEIRYA;
        // if (e == SampleEnum.TAHA) {
        //     System.out.println("HI");
        // } else {
        //     System.out.println("BYE");
        // }

        switch(e) {
            case TAHA:
            System.out.println("HI");
            break;
            case AEIRYA:
            System.out.println("BYE");
        }
    }
}
