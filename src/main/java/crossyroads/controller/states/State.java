package crossyroads.controller.states;

import java.io.IOException;

public interface State {
    public void step() throws IOException;
}
