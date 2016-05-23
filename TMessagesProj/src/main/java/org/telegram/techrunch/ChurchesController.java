package org.telegram.techrunch;

import org.telegram.techrunch.domain.Church;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 5/20/16.
 */
public class ChurchesController {

    private static volatile ChurchesController Instance = null;

    private List<Church> mChurchesList = new ArrayList<>();

    public static ChurchesController getInstance() {
        ChurchesController localInstance = Instance;
        if (localInstance == null) {
            synchronized (ChurchesController.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    Instance = localInstance = new ChurchesController();
                }
            }
        }
        return localInstance;
    }

    ChurchesController() {
        mChurchesList.add(new Church("first", "url"));
        mChurchesList.add(new Church("second", "url"));
    }

    public List<Church> churchesList() {
        return mChurchesList;
    }
}
