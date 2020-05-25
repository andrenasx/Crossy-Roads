package crossyroads.controller;

import java.io.IOException;

public interface State {
    void step() throws IOException;
}
