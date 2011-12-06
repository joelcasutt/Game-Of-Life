package ch.javaCourse.gameOfLife;

public class GameOfLifeStarter {
    public static void main(String[] args) {
	Controller controller=null;
	if (args.length==2) {
	    controller = new Controller(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
	} else if (args.length==1) {
	    controller = new Controller(Integer.parseInt(args[0]));
	}else if (args.length>2) {
	    System.out.println("too many arguments");
	    System.exit(0);
	} else {
	    controller = new Controller();
	}
	controller.showView();
    }
}
