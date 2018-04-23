package org.bigtheta.goals.repo.impl.postgres;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PgRepoTest {

    @Test
    void main() {
        ArrayList<String> list = null;

        slkdj(list);
        System.out.println(list);


    }

    private void slkdj(ArrayList list) {

        list = new ArrayList<>();
        list.add("blah");
    }
}