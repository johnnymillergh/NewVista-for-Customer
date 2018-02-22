package com.jm.newvista.util;

import java.util.ArrayList;
import java.util.List;

public class SeatLocationUtil {
    public static class SeatLocation {
        private int row;
        private int col;

        public SeatLocation() {
        }

        public SeatLocation(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }

    public static String generate(List<SeatLocation> seatLocations) {
        StringBuffer stringBuffer = new StringBuffer();
        for (SeatLocation seatLocation : seatLocations) {
            stringBuffer.append("(" + seatLocation.getRow() + "," + seatLocation.getCol() + ") ");
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    public static List<SeatLocation> parse(String seatLocationsString) {
        List<SeatLocation> seatLocations = new ArrayList<>();
        String[] seatLocationsArray = seatLocationsString.split(" ");
        for (String seatLocationStr : seatLocationsArray) {
            String[] seatLocationRowCol = seatLocationStr.substring(1, seatLocationStr.length() - 1).split(",");
            int row = Integer.parseInt(seatLocationRowCol[0]);
            int col = Integer.parseInt(seatLocationRowCol[1]);
            SeatLocation seatLocation = new SeatLocation();
            seatLocation.setRow(row);
            seatLocation.setCol(col);
            seatLocations.add(seatLocation);
        }
        return seatLocations;
    }

//    public static void main(String[] args) {
//        List<SeatLocation> seatLocations = new ArrayList<>();
//        SeatLocation seatLocation;
//        for (int i = 1; i < 4; i++) {
//            for (int j = 1; j < 4; j++) {
//                seatLocation = new SeatLocation();
//                seatLocation.setRow(i);
//                seatLocation.setCol(j);
//                seatLocations.add(seatLocation);
//            }
//        }
//        String seatLocationGenerated = generate(seatLocations);
//        System.out.println(seatLocationGenerated);
//
//        List<SeatLocation> seatLocations1 = parse(seatLocationGenerated);
//        System.out.println(seatLocations1);
//    }
}
