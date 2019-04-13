package com.infoshareacademy.homeworks;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.averagingDouble;
import static java.util.stream.Collectors.groupingBy;

public class GradeService {

    public String[][] calculateAverage(String[][] data) {

        DecimalFormatSymbols decimalSymbol = new DecimalFormatSymbols(Locale.getDefault());
        decimalSymbol.setDecimalSeparator('.');
        DecimalFormat myFormat = new DecimalFormat("0.00");
        myFormat.setDecimalFormatSymbols(decimalSymbol);

        if (data == null || data.length == 0) {
            return new String[][]{{}};
        }

        Set setOut = Arrays.stream(data)
                .collect(groupingBy(s -> s[0], averagingDouble(s -> parseInt(s[1]))))
                .entrySet();

        String[][] finalOut = new String[setOut.size()][2];
        List<String> tempList = new ArrayList<>();
        setOut.forEach(a -> tempList.add(a.toString()));

        Collections.sort(tempList);

        int i = 0;
        for (String s : tempList) {
            finalOut[i][0] = s.split("=")[0];
            finalOut[i][1] = (myFormat.format(Double.parseDouble(s.split("=")[1])));
            i++;
        }

        return finalOut;
    }
}
