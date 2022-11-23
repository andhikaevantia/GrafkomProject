package GameEngine.Engine;

import static org.lwjgl.glfw.GLFW.GLFW_CURSOR_DISABLED;
import static org.lwjgl.glfw.GLFW.glfwSetInputMode;
import static org.lwjgl.opengl.GL11C.*;

public class GameEngine implements Runnable{
    private Window window;
    private IGameLogic gameLogic;
    private MouseInput mouseInput;
    public GameEngine(String windowTitle,int width,int height, boolean vSync,
                      IGameLogic gameLogic){
        window = new Window(windowTitle,width,height,vSync);
        this.gameLogic = gameLogic;
        mouseInput = new MouseInput();

    }

    @Override
    public void run() {
        try{
            init();
            gameLoop();
        }catch (Exception excp){
            excp.printStackTrace();
        }finally {
            cleanup();
        }
    }

    public void init() throws Exception {
        window.init();
        gameLogic.init();
        mouseInput.init(window);
    }
    public void gameLoop() throws Exception {
        boolean running = true;
        while(running && !window.windowShouldClose()){
            glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);
            input();
            update();
            render();
        }
    }
    public void cleanup(){gameLogic.cleanup();}
    public void input(){gameLogic.input(window,mouseInput);mouseInput.input(window);}
    public void update() throws Exception {
        gameLogic.update(mouseInput);

    }
    public void render(){
        gameLogic.render(window);
        window.update();
    }
}
