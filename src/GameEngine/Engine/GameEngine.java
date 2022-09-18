package GameEngine.Engine;

import static org.lwjgl.opengl.GL11.*;

public class GameEngine implements Runnable{
    private final Window window;
    private final IGameLogic gameLogic;
    private final MouseInput mouseInput;
    public GameEngine(String windowTitle, int width, int height, boolean vSync, IGameLogic gameLogic) throws Exception {
        window = new Window(windowTitle, width, height, vSync);
        this.gameLogic = gameLogic;
        mouseInput = new MouseInput();
    }

    @Override
    public void run() {
        try {
            init();
            gameLoop();
        } catch (Exception excp) {
            excp.printStackTrace();
        } finally {
            cleanup();
        }
    }
    protected void init() throws Exception {
        window.init();
        mouseInput.init(window);
        gameLogic.init();
    }
    protected void gameLoop() throws Exception {
        boolean running = true;
        while (running && !window.windowShouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            input();
            update();
            render();
        }
    }
    protected void cleanup() {
        gameLogic.cleanup();
    }
    protected void input() {
        mouseInput.input(window);
        gameLogic.input(window,mouseInput);
    }

    protected void update() throws Exception {
        gameLogic.update(mouseInput);
    }

    protected void render() {
        gameLogic.render(window);
        window.update();
    }
}
