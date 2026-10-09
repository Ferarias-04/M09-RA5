package iticbcn.xifratge;

public class TestXifratge {
    public static void main(String[] args) {
        AlgorismeFactory[] aFactory = {
            new AlgorismeAES(), new AlgorismeMonoalfabetic(),
            new AlgorismePolialfabetic(), new AlgorismeRotX()
        };

        String[] aNames = {"AES", "Monoalfabètic", "Polialfabètic", "RotX"};

        String[] msgs = {};
    }
}
