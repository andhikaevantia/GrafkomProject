package GameEngine.Engine;

public interface IGameLogic {
    void init() throws Exception;

    void input(Window window,MouseInput mouseInput);

    void update(MouseInput mouseInput) throws Exception;

    void render(Window window);

    void cleanup();
}
