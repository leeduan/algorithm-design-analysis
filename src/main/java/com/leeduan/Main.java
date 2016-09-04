package com.leeduan;

import com.leeduan.graph.Contraction;
import com.leeduan.graph.StrongComponents;
import com.leeduan.sort.DivideAndConquer;

public class Main {

    /**
     * Execute using `sbt run`.
     * @param args
     */
    public static void main(String [] args) {
        DivideAndConquer.run();
        Contraction.run();
        StrongComponents.run();
    }

}
