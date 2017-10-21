package util;

import com.sun.istack.internal.NotNull;

/**
 * Created by Tomas on 10/17/17.
 */
public interface Action {

    public void performAction(@NotNull Option... options);

}
