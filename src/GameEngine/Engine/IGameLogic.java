package GameEngine.Engine;

public interface IGameLogic {
    void init() throws Exception;

    void input(Window window);

    void update();

    void render(Window window);

    void cleanup();
}
