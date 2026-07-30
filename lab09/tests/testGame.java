import gameoflife.GameOfLife;
import org.junit.Assert;
import org.junit.Test;
import tileengine.TETile;
import tileengine.Tileset;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class testGame {

    @Test
    public void testGetNeighbourNum() {
        TETile[][] testInput = new TETile[][] {
                {Tileset.CELL, Tileset.CELL, Tileset.NOTHING},
                {Tileset.NOTHING, Tileset.NOTHING, Tileset.NOTHING},
                {Tileset.NOTHING, Tileset.NOTHING, Tileset.NOTHING}
        };
        GameOfLife student = new GameOfLife(testInput, true);

        int n = student.getNeighbourNum(0,0,testInput);
        assertThat(n).isEqualTo(1);

        n = student.getNeighbourNum(2,2,testInput);
        assertThat(n).isEqualTo(0);

        n = student.getNeighbourNum(1,1,testInput);
        assertThat(n).isEqualTo(2);

        TETile[][] testInput1 = new TETile[][] {
                {Tileset.CELL, Tileset.CELL, Tileset.NOTHING},
                {Tileset.NOTHING, Tileset.CELL, Tileset.NOTHING},
                {Tileset.NOTHING, Tileset.NOTHING, Tileset.NOTHING}
        };
        GameOfLife student1 = new GameOfLife(testInput1, true);

        n = student.getNeighbourNum(1,0,testInput1);
        assertThat(n).isEqualTo(3);

        n = student.getNeighbourNum(1,2,testInput1);
        assertThat(n).isEqualTo(2);
    }
    private void checkState(TETile[][] student, TETile[][] expected) {
        for (int i = 0; i < student.length; i++) {
            for (int j = 0; j < student[0].length; j++) {
                assertThat(student[i][j].description()).isEqualTo(expected[i][j].description());
            }
        }
    }
    @Test
    public void oneGeneration() {
        TETile[][] testInput = new TETile[][] {
                {Tileset.CELL, Tileset.CELL, Tileset.CELL},
                {Tileset.NOTHING, Tileset.NOTHING, Tileset.NOTHING},
                {Tileset.NOTHING, Tileset.NOTHING, Tileset.NOTHING}
        };

        TETile[][] result = new TETile[][] {
                {Tileset.NOTHING, Tileset.CELL, Tileset.NOTHING},
                {Tileset.NOTHING, Tileset.CELL, Tileset.NOTHING},
                {Tileset.NOTHING, Tileset.NOTHING, Tileset.NOTHING}
        };

        GameOfLife student = new GameOfLife(testInput, true);

        // Generate next state from the provided input.
        TETile[][] nextState = student.nextGeneration(testInput);

        // Height and width need to stay the same before and after the generation.
        assertThat(testInput.length).isEqualTo(nextState.length);
        assertThat(testInput[0].length).isEqualTo(nextState[0].length);

        // Check that the next generation state is as expected.
        checkState(nextState, result);
    }

    @Test
    public void testSaveBoard() {
        TETile[][] result = new TETile[][] {
                {Tileset.NOTHING, Tileset.CELL, Tileset.NOTHING},
                {Tileset.CELL, Tileset.CELL, Tileset.CELL},
                {Tileset.CELL, Tileset.NOTHING, Tileset.NOTHING},
                {Tileset.NOTHING, Tileset.CELL, Tileset.NOTHING}
        };
        GameOfLife student = new GameOfLife(result, true);
        student.saveBoard();
        student.loadBoard("src/save.txt");
    }

    @Test
    public void testLoadBoard() {
        TETile[][] result = new TETile[][] {
                {Tileset.NOTHING, Tileset.CELL, Tileset.NOTHING},
                {Tileset.NOTHING, Tileset.CELL, Tileset.CELL},
                {Tileset.CELL, Tileset.NOTHING, Tileset.NOTHING},
                {Tileset.NOTHING, Tileset.CELL, Tileset.NOTHING}
        };
        GameOfLife student = new GameOfLife(result, true);
        student.saveBoard();
        GameOfLife l = new GameOfLife(student.loadBoard("src/save.txt"), true);
        l.saveBoard();
    }
}
