import Model.*;
import  TextUI.*;
public class main {
    public static void main(String args[]) {
        LensManager manager = new LensManager();
        textUI ui = new textUI(manager);
        ui.show();
    }
}
