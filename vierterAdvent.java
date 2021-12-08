import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class vierterAdvent {



    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader("Input/aoc_day4.txt"));
        int[] randomNumbers = Arrays.stream(br.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int countBingoFelderZeilen = 0;
        int counter = 0;
        int[][] bingoFeld = new int[countBingoFelderZeilen][];


        String line;


        String[] bingoFelderZeilen = new String[countBingoFelderZeilen];


        while((line = br.readLine()) != null){
            if(line.equals("")){
                String[] copyBingoFeld = bingoFelderZeilen;
                countBingoFelderZeilen += 5;
                bingoFelderZeilen = new String [countBingoFelderZeilen];
                if(countBingoFelderZeilen > 5) {
                    for(int i = 0; i < countBingoFelderZeilen-5; i++) {
                        bingoFelderZeilen[i] = copyBingoFeld[i];
                    }
                }
            }
            else{
                bingoFelderZeilen[counter] = line;
                counter++;

            }
        }
        br.close();


        int[][] bingoFelder = new int[bingoFelderZeilen.length][];

        for(int i = 0; i < bingoFelderZeilen.length; i++){
            bingoFelderZeilen[i] = bingoFelderZeilen[i].replaceAll("^ +|( )+", "$1");
            bingoFelder[i] = Arrays.stream(bingoFelderZeilen[i].split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[][][] alleBingoFelder = new int[bingoFelderZeilen.length/5][5][5];
        int countBingoFeld = 0;

        for(int i = 0; i < bingoFelderZeilen.length/5; i++){ // i: Nummer des Bingofeldes
            for (int j = 0; j < 5; j++) {                      // j: Spalte des Feldes
                for (int k = 0; k < 5; k++) {                  // k: Zeile des Feldes
                    alleBingoFelder[i][j][k] = bingoFelder[j + countBingoFeld][k];
                }
            }
            countBingoFeld += 5;
        }


        boolean[][][] markierung = new boolean[alleBingoFelder.length][5][5];
        int summeEinesFeldes = 0;



        //Endergebnis: ganze Zeile/Spalte eines BingoFeldes wurde markiert


      for(int r = 0; r < randomNumbers.length; r++) { //nacheinander randomNumber picken

          for (int i = 0; i < alleBingoFelder.length; i++) {
              for (int j = 0; j < 5; j++) {

                  //markierte Zeile gefunden
                  if (markierung[i][j][0] && markierung[i][j][1] && markierung[i][j][2] && markierung[i][j][3] && markierung[i][j][4]){

                      System.out.println(i);  // Ausgabe des Bingofeldes, welches eine Zeile/Spalte voll hat
                      System.out.println(randomNumbers[r-1]);


                      int markiertSumme = 0;

                      for(int y = 0; y < 5; y++){
                          for(int z = 0; z < 5; z++){
                              summeEinesFeldes += alleBingoFelder[i][y][z];
                              if(markierung[i][y][z]){
                                  markiertSumme += alleBingoFelder[i][y][z];
                              }
                          }
                      }
                      int unmarkiertSumme = 0;
                      unmarkiertSumme = summeEinesFeldes - markiertSumme;
                      System.out.println(markiertSumme); //Ausgabe der Summe aller unmarkierten Zahlen
                      int ergebnis = markiertSumme * randomNumbers[r];
                      System.out.println(ergebnis); //Ausgabe des ergebnis
                      return;

                  }

                  //markierte Spalte gefunden
                  else if(markierung[i][0][j] && markierung[i][1][j] && markierung[i][2][j] && markierung[i][3][j] && markierung[i][4][j]){


                      System.out.println(randomNumbers[r-1]);


                      int markiertSumme = 0;

                      for(int y = 0; y < 5; y++){
                          for(int z = 0; z < 5; z++){
                              summeEinesFeldes += alleBingoFelder[i][y][z];
                              if(markierung[i][y][z]){
                                  markiertSumme += alleBingoFelder[i][y][z];
                              }
                          }
                      }

                      int unmarkiertSumme = 0;


                      unmarkiertSumme = summeEinesFeldes - markiertSumme;
                      System.out.println(markiertSumme);  //Ausgabe der Summe aller unmarkierten Zahlen
                      int ergebnis = unmarkiertSumme * randomNumbers[r-1];
                      System.out.println(ergebnis); //Ausgabe des ergebnis
                      return;
                  }
              }
          }


          //alle Bingofelder durchgehen und falls neue Zahl vorkommt, dann markieren
          for(int i = 0; i < alleBingoFelder.length; i++){
              for(int j = 0; j < 5; j++){
                  for(int k = 0; k < 5; k++){
                      if(alleBingoFelder[i][j][k] == randomNumbers[r]){
                          markierung[i][j][k] = true;
                      }
                  }
              }
          }
      }
    }
}
