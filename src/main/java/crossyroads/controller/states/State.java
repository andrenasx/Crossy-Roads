package crossyroads.controller.states;

import java.io.IOException;

public interface State {
    void step() throws IOException;
}
