/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package statesAndCapitals;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StatesAndCapitals
{

    String statesAndCapitalsFilePath = "./src/main/resources/us_states_and_capitals.json";

    public ArrayList<StateInfo> readStatesAndCapitals() throws IOException
    {
        File statesAndCapitalsFile = new File(statesAndCapitalsFilePath);
        ArrayList<StateInfo> states;

        try(FileReader statesAndCapitalsFileReader = new FileReader(statesAndCapitalsFile))
        {
            final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                @Override
                public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
                };
            }).create();

            states = gson.fromJson(statesAndCapitalsFileReader, new TypeToken<List<StateInfo>>() {}.getType());
        }

        return states;
    }

    public void testStatesAndCapitals() throws IOException
    {
        ArrayList<StateInfo> states = readStatesAndCapitals();

        Map<String, Boolean> testResults = new LinkedHashMap<>();

        // Rules:
        // 1. No iteration or loops. Submission containing any of the following will result in 0 points for the assignment:
        //    1A. No for() or while() or do-while() loops.
        //    1B. Don't try to simulate a loop in other ways, like using if() and a counter variable.
        //    1C. Don't use the Java stream operations .forEach(), .forEachOrdered(), and peek(). (You can use them for debugging, but you can't submit them!)
        // 2. Don't cheese answers by submitting them directly. If an answer is a one-word String, or a boolean, don't just submit the string or the boolean. If you do, you will not get credit for that test.
        // 3. Don't make intermediate data structures. These should all be "one-liners". Use only the variable provided.

        // Starter hints:
        // 1. Most of your answers will start with states.stream().
        // 2. Many of your answers will end with collect(toList()).

        // ***** Basic *****

        // B1. Submit the first five states
        // Use limit()

        List<StateInfo> firstFiveStates = null;

        firstFiveStates = states.stream().limit(5).collect(toList());

        testResults.put("B1", StatesAndCapitalsCheck.basic1(firstFiveStates));

        // B2. Submit the last five states
        // Use skip()

        List<StateInfo> lastFiveStates = null;

        lastFiveStates = states.stream().skip(45).collect(toList());

        testResults.put("B2", StatesAndCapitalsCheck.basic2(lastFiveStates));

        // B3. From 1-20, submit the first 5 numbers
        // Use limit()

        List<Integer> firstFiveNumbers = IntStream.range(1, 6).boxed().collect(toList());

        testResults.put("B3", StatesAndCapitalsCheck.basic3(firstFiveNumbers));

        // B4. From 1-20, submit the last 5 numbers
        // Use skip()

        List<Integer> lastFiveNumbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20).skip(15).collect(toList());

        testResults.put("B4", StatesAndCapitalsCheck.basic4(lastFiveNumbers));

        // B5. Submit the total number of states
        // Use count() (or counting())
        // PS: Don't use states.size(). It's easier and IntelliJ will even warn you not to do things this way. But I want you to understand how to use count() (or counting()).

        Long statesNumber = null;

        statesNumber = states.stream().count();

        testResults.put("B5", StatesAndCapitalsCheck.basic5(statesNumber));

        // ***** Intermediate (any / first / all / none matches) *****

        // I1. Submit any state where the state bird is "cardinal"
        // Use findAny() or findFirst(), and orElseThrow()
        // Can use filter()

        StateInfo cardinalState = null;

        cardinalState = states.stream().filter(state -> state.getStateBird().equals("cardinal")).findAny().orElseThrow();

        testResults.put("I1", StatesAndCapitalsCheck.int1(cardinalState));

        // I2. Find if any state's lowest elevation is less than 0
        // Use anyMatch()

        Boolean isAnyStateLessThan0Elevation = null;

        isAnyStateLessThan0Elevation = states.stream().anyMatch(state -> state.getLowestElevationInFeet() < 0);

        testResults.put("I2", StatesAndCapitalsCheck.int2(isAnyStateLessThan0Elevation));

        // I3. Find if any state's highest elevation is greater than 21000
        // Use anyMatch()

        Boolean isAnyStateGreaterThan21000Elevation = null;

        isAnyStateGreaterThan21000Elevation = states.stream().anyMatch(state -> state.getHighestElevationInFeet() > 1000);

        testResults.put("I3", StatesAndCapitalsCheck.int3(isAnyStateGreaterThan21000Elevation));

        // I4. Find if all states have an anthem
        // Use allMatch()

        Boolean doAllStatesHaveAnAnthem = null;

        doAllStatesHaveAnAnthem = states.stream().allMatch(state -> state.getStateAnthem() != null);

        testResults.put("I4", StatesAndCapitalsCheck.int4(doAllStatesHaveAnAnthem));

        // I5. Find if no state has a one-word motto
        // Use noneMatch()
        // Can use String.split()

        Boolean doNoStatesHaveAOneWordMotto = null;

        doNoStatesHaveAOneWordMotto = states.stream().noneMatch(state -> state.getStateMotto().split("\\s+").length==1);

        testResults.put("I5", StatesAndCapitalsCheck.int5(doNoStatesHaveAOneWordMotto));

        // ***** Advanced 1 (aggregation) *****

        // A11. Submit the average yearly precipitation across all state capitals
        // Use collect(averagingDouble())

        Double averageYearlyPrecipitationAcrossStateCapitals = null;

        averageYearlyPrecipitationAcrossStateCapitals = states.stream().collect(averagingDouble(state -> state.getCapital().getAverageYearlyPrecipitationInInches()));

        testResults.put("A11", StatesAndCapitalsCheck.adv11(averageYearlyPrecipitationAcrossStateCapitals));

        // A12. Submit the total yearly precipitation across all state capitals
        // Use collect(summingInt())  (PS: IntelliJ will warn you to use the version on the next line, but it's useful to see how summingInt() works)
        // Or use mapToInt() and sum()

        Integer totalYearlyPrecipitationAcrossStateCapitals = null;

        totalYearlyPrecipitationAcrossStateCapitals = states.stream().collect(summingInt(state -> state.getCapital().getAverageYearlyPrecipitationInInches()));

        testResults.put("A12", StatesAndCapitalsCheck.adv12(totalYearlyPrecipitationAcrossStateCapitals));

        // A13. Submit how many states are in each time zone (or group of time zones)
        // Use collect(groupingBy()) and counting()

        Map<String, Long> numberOfStatesByTimeZone = null;

        numberOfStatesByTimeZone = states.stream().collect(groupingBy(state -> state.getTimeZones().stream().collect(Collectors.joining(",")),counting()));

        testResults.put("A13", StatesAndCapitalsCheck.adv13(numberOfStatesByTimeZone));

        // A14. Submit how many state capitals are in each time zone
        // Use collect(groupingBy()) and counting()

        Map<String, Long> numberOfStateCapitalsByTimeZone = null;

        numberOfStateCapitalsByTimeZone = states.stream().collect(groupingBy(state -> state.getCapital().getTimeZone(), counting());

        testResults.put("A14", StatesAndCapitalsCheck.adv14(numberOfStateCapitalsByTimeZone));

        // ***** Advanced 2 (requires map() + additional technique(s)) *****

        // A21. Submit all state trees, sorted alphabetically (ascending)
        // Use sorted() and map()

        List<String> stateTreesSortedAscending = null;

        stateTreesSortedAscending = states.stream().map(state -> state.getStateTree()).sorted().collect(toList());

        testResults.put("A21", StatesAndCapitalsCheck.adv21(stateTreesSortedAscending));

        // A22. Submit all state names, separated by "; "
        // Use collect(joining()) and map()

        String allStateNamesSemicolonDelimited = null;

        allStateNamesSemicolonDelimited = states.stream().map(StateInfo::getStateName).collect(joining("; "));

        testResults.put("A22", StatesAndCapitalsCheck.adv22(allStateNamesSemicolonDelimited));

        // A23. Submit all distinct state birds
        // Use distinct() and map()

        List<String> allDistinctStateBirds = null;

        allDistinctStateBirds = states.stream().map(state -> state.getStateBird()).distinct().collect(toList());

        testResults.put("A23", StatesAndCapitalsCheck.adv23(allDistinctStateBirds));

        // A24. Submit all distinct state birds, but with any kind of mockingbird removed
        // Use distinct(), map(), and filter()

        List<String> allDistinctStateBirdsMinusMockingbirds = null;

        allDistinctStateBirdsMinusMockingbirds = states.stream().map(state -> state.getStateBird()).distinct().filter(bird -> !bird.contains("mocking")).collect(toList());

        testResults.put("A24", StatesAndCapitalsCheck.adv24(allDistinctStateBirdsMinusMockingbirds));

        // A25. Submit the number of distinct state birds
        // Use collect(counting()), map(), and distinct()
        // PS: Don't use count(). IntelliJ will warn you but I want you to see how counting() works.

        Long numberOfDistinctStateBirds = null;

        numberOfDistinctStateBirds = states.stream().map(state -> state.getStateBird()).distinct().collect(counting());

        testResults.put("A25", StatesAndCapitalsCheck.adv25(numberOfDistinctStateBirds));

        // ***** Advanced 3 (custom comparators) *****

        // A31. Submit the max state elevation
        // Use max(), orElseThrow(), and map()
        // Can use map() and Comparator.naturalOrder()

        Integer maxStateElevation = null;

        maxStateElevation = states.stream().map(state -> state.getHighestElevationInFeet()).max(Comparator.naturalOrder()).orElseThrow();

        testResults.put("A31", StatesAndCapitalsCheck.adv31(maxStateElevation));

        // A32. Submit the earliest date a state entered the union
        // Use min(), orElseThrow(), and map()
        // Can use map() and LocalDate::compareTo

        LocalDate earliestDateStateEnteredUnion = null;

        earliestDateStateEnteredUnion = states.stream().map(state -> state.getDateAdmittedToUnion()).min(Comparator.naturalOrder()).orElseThrow();

        testResults.put("A32", StatesAndCapitalsCheck.adv32(earliestDateStateEnteredUnion));

        // A33. Submit the state with the least distance between its highest and lowest points
        // Use min(), comparing(), and orElse()

        StateInfo stateWithLeastDistanceBetweenHighAndLowPoints = null;

        testResults.put("A33", StatesAndCapitalsCheck.adv33(stateWithLeastDistanceBetweenHighAndLowPoints));

        // ***** Advanced 4 (fun with mapping) *****

        // A41. Submit all state and capital names together, with each state name followed by its capital name
        // Use flatMap() and Stream.of() (for the pairs)------------------

        List<String> allStateAndCapitalNames = null;

        testResults.put("A41", StatesAndCapitalsCheck.adv41(allStateAndCapitalNames));

        // A42. Submit all state and capital names together, but group each state and capital pair into a list
        // Use map(), two instances of collect(toList()), and Stream.of() (for the pairs)

        List<List<String>> allStateAndCapitalNamesTogetherAsLists = null;

        testResults.put("A42", StatesAndCapitalsCheck.adv42(allStateAndCapitalNamesTogetherAsLists));

        // A43. Submit all state and capital names together, but group each state as a key, and each capital as a value, in a Map
        // Use collect(toMap())

        Map<String, String> stateNameToCapitalNamesMap = null;

        testResults.put("A43", StatesAndCapitalsCheck.adv43(stateNameToCapitalNamesMap));

        // ***** Expert (for people who like a challenge) *****
        // NOTE: You will * NOT * be graded on these!

        // E1. Submit a list of all the denonyms that do not contain the state's name in them
        // Use flatMap(), filter()

        List<String> allDenonymsThatDoNotContainStateName = null;

        testResults.put("E1", StatesAndCapitalsCheck.expert1(allDenonymsThatDoNotContainStateName));

        // E2. Submit the total number of Honolulu's sister cities that start with the letters "Ca"
        // Use filter(), flatMap(), and count()
        // PS: Don't cheat by using an intermediate data structure for Honolulu!

        Long totalNumberOfHonoluluSisterCitiesStartingWithCa = null;

        testResults.put("E2", StatesAndCapitalsCheck.expert2(totalNumberOfHonoluluSisterCitiesStartingWithCa));

        // E3. Submit all the countries of the world where there are no sister cities of a US capital
        // (See a list of world countries at the bottom of this file)
        // Use Arrays.stream(), flatMap(), map(), and collect(toList())
        // If you need a hint, look inside the E3 answer checking function

        List<String> countriesOfTheWorldWithNoUSCapitalSisterCities = null;

        testResults.put("E3", StatesAndCapitalsCheck.expert3(countriesOfTheWorldWithNoUSCapitalSisterCities));

        // E4. Submit the two names of neighboring states (order doesn't matter) with the most difference between their highest points, separated by a space
        // Example (wrong) answer: "Alaska Hawaii"
        // Abandon hope, all ye who enter here; if you insist on trying, you should think about using filter(), map(), max(), orElse(), and some abuse of AbstractMap.SimpleEntry

        String statesWithLargestDifferenceBetweenHighestElevations = null;

        testResults.put("E4", StatesAndCapitalsCheck.expert4(statesWithLargestDifferenceBetweenHighestElevations));

        // E5. Submit the two names of the closest state capitals (order doesn't matter), separated by a space
        // Example (wrong) answer: "Juneau Honolulu"
        // Note there is a latLongDist() function below to help
        // Similar principles as the last answer, and just as difficult; if you still want to try, you should think about using filter(), map(), min(), orElse(), and some abuse of AbstractMap.SimpleEntry

        String closestStateCapitals = null;

        testResults.put("E5", StatesAndCapitalsCheck.expert5(closestStateCapitals));

        // ***** End Tests *****

        System.out.println(testResults);

        // Try this function out for a better idea of what map() vs. flatMap() does

        //mapAndFlatMapExample();
    }

    // From https://stackoverflow.com/a/123305/16889809

    private double latLongDist(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (earthRadius * c);

        return dist;
    }

    private void mapAndFlatMapExample()
    {
        List<String> singlyNestedList =
                Arrays.asList("a", "b");

        List<List<String>> doublyNestedList =
                Arrays.asList(
                        Arrays.asList("a", "b"),
                        Arrays.asList("c", "d"),
                        Arrays.asList("e", "f")
                );

        List<List<List<String>>> triplyNestedList =
                Arrays.asList(
                        Arrays.asList(
                                Arrays.asList("a", "b"),
                                Arrays.asList("c", "d"),
                                Arrays.asList("e", "f")
                        ),
                        Arrays.asList(
                                Arrays.asList("g", "h"),
                                Arrays.asList("i", "j"),
                                Arrays.asList("k", "l"),
                                Arrays.asList("m", "n"),
                                Arrays.asList("o", "p")
                        )
                );

        System.out.println("Singly-nested list: ");
        System.out.println(singlyNestedList);
        System.out.println("Doubly-nested list: ");
        System.out.println("[");
        for (List<String> innerList : doublyNestedList)
        {
            System.out.println("    " + innerList);
        }
        System.out.println("]");
        System.out.println("Triply-nested list: ");
        System.out.println("[");
        for (List<List<String>> innerList : triplyNestedList)
        {
            System.out.println("    [");
            for (List<String> innerInnerList : innerList)
            {
                System.out.println("        " + innerInnerList);
            }
            System.out.println("    ]");
        }
        System.out.println("]");

        List<String> uppercaseSinglyNestedListWithMap = singlyNestedList
                .stream()
                .map(String::toUpperCase)
                .collect(toList());
        System.out.println("Uppercase singly-nested list: " + uppercaseSinglyNestedListWithMap);

        List<String> uppercaseDoublyNestedListWithFlatMap = doublyNestedList
                .stream()
                .flatMap(List::stream)
                .map(String::toUpperCase)
                .collect(toList());
        System.out.println("Uppercase doubly-nested list, flat-mapped once: " + uppercaseDoublyNestedListWithFlatMap);

        List<List<String>> triplyNestedListWithSingleFlatMap = triplyNestedList
                .stream()
                .flatMap(List::stream)
                .collect(toList());
        System.out.println("Triply-nested list, flat-mapped once: " + triplyNestedListWithSingleFlatMap);

        List<List<String>> uppercaseTriplyNestedListWithSingleFlatMap = triplyNestedList
                .stream()
                .flatMap(List::stream)
                .map(s -> s.stream().map(t -> t.toUpperCase(Locale.ROOT)).collect(toList()))
                .collect(toList());
        System.out.println("Uppercase triply-nested list, flat-mapped once: " + uppercaseTriplyNestedListWithSingleFlatMap);

        List<String> uppercaseTriplyNestedListWithDoubleFlatMap = triplyNestedList
                .stream()
                .flatMap(List::stream)
                .flatMap(List::stream)
                .map(String::toUpperCase)
                .collect(toList());
        System.out.println("Uppercase triply-nested list, flat-mapped twice: " + uppercaseTriplyNestedListWithDoubleFlatMap);
    }

    // List from https://gist.github.com/marijn/396531/188caa065e3cd319fed7913ee3eecf5eec541918#gistcomment-2196888

    private String[] allCountriesList = {
            "Afghanistan",
            "Åland Islands",
            "Albania",
            "Algeria",
            "American Samoa",
            "Andorra",
            "Angola",
            "Anguilla",
            "Antarctica",
            "Antigua and Barbuda",
            "Argentina",
            "Armenia",
            "Aruba",
            "Australia",
            "Austria",
            "Azerbaijan",
            "Bahamas",
            "Bahrain",
            "Bangladesh",
            "Barbados",
            "Belarus",
            "Belgium",
            "Belize",
            "Benin",
            "Bermuda",
            "Bhutan",
            "Bolivia (Plurinational State of)",
            "Bonaire and Sint Eustatius and Saba",
            "Bosnia and Herzegovina",
            "Botswana",
            "Bouvet Island",
            "Brazil",
            "British Indian Ocean Territory",
            "United States Minor Outlying Islands",
            "Virgin Islands (British)",
            "Virgin Islands (U.S.)",
            "Brunei Darussalam",
            "Bulgaria",
            "Burkina Faso",
            "Burundi",
            "Cambodia",
            "Cameroon",
            "Canada",
            "Cabo Verde",
            "Cayman Islands",
            "Central African Republic",
            "Chad",
            "Chile",
            "China",
            "Christmas Island",
            "Cocos (Keeling) Islands",
            "Colombia",
            "Comoros",
            "Congo",
            "Congo (Democratic Republic of the)",
            "Cook Islands",
            "Costa Rica",
            "Croatia",
            "Cuba",
            "Curaçao",
            "Cyprus",
            "Czech Republic",
            "Denmark",
            "Djibouti",
            "Dominica",
            "Dominican Republic",
            "Ecuador",
            "Egypt",
            "El Salvador",
            "Equatorial Guinea",
            "Eritrea",
            "Estonia",
            "Ethiopia",
            "Falkland Islands (Malvinas)",
            "Faroe Islands",
            "Fiji",
            "Finland",
            "France",
            "French Guiana",
            "French Polynesia",
            "French Southern Territories",
            "Gabon",
            "Gambia",
            "Georgia",
            "Germany",
            "Ghana",
            "Gibraltar",
            "Greece",
            "Greenland",
            "Grenada",
            "Guadeloupe",
            "Guam",
            "Guatemala",
            "Guernsey",
            "Guinea",
            "Guinea-Bissau",
            "Guyana",
            "Haiti",
            "Heard Island and McDonald Islands",
            "Holy See",
            "Honduras",
            "Hong Kong",
            "Hungary",
            "Iceland",
            "India",
            "Indonesia",
            "Côte d'Ivoire",
            "Iran (Islamic Republic of)",
            "Iraq",
            "Ireland",
            "Isle of Man",
            "Israel",
            "Italy",
            "Jamaica",
            "Japan",
            "Jersey",
            "Jordan",
            "Kazakhstan",
            "Kenya",
            "Kiribati",
            "Kuwait",
            "Kyrgyzstan",
            "Lao People's Democratic Republic",
            "Latvia",
            "Lebanon",
            "Lesotho",
            "Liberia",
            "Libya",
            "Liechtenstein",
            "Lithuania",
            "Luxembourg",
            "Macao",
            "Macedonia (the former Yugoslav Republic of)",
            "Madagascar",
            "Malawi",
            "Malaysia",
            "Maldives",
            "Mali",
            "Malta",
            "Marshall Islands",
            "Martinique",
            "Mauritania",
            "Mauritius",
            "Mayotte",
            "Mexico",
            "Micronesia (Federated States of)",
            "Moldova (Republic of)",
            "Monaco",
            "Mongolia",
            "Montenegro",
            "Montserrat",
            "Morocco",
            "Mozambique",
            "Myanmar",
            "Namibia",
            "Nauru",
            "Nepal",
            "Netherlands",
            "New Caledonia",
            "New Zealand",
            "Nicaragua",
            "Niger",
            "Nigeria",
            "Niue",
            "Norfolk Island",
            "Korea (Democratic People's Republic of)",
            "Northern Mariana Islands",
            "Norway",
            "Oman",
            "Pakistan",
            "Palau",
            "Palestine (State of)",
            "Panama",
            "Papua New Guinea",
            "Paraguay",
            "Peru",
            "Philippines",
            "Pitcairn",
            "Poland",
            "Portugal",
            "Puerto Rico",
            "Qatar",
            "Republic of Kosovo",
            "Réunion",
            "Romania",
            "Russian Federation",
            "Rwanda",
            "Saint Barthélemy",
            "Saint Helena and Ascension and Tristan da Cunha",
            "Saint Kitts and Nevis",
            "Saint Lucia",
            "Saint Martin (French part)",
            "Saint Pierre and Miquelon",
            "Saint Vincent and the Grenadines",
            "Samoa",
            "San Marino",
            "Sao Tome and Principe",
            "Saudi Arabia",
            "Senegal",
            "Serbia",
            "Seychelles",
            "Sierra Leone",
            "Singapore",
            "Sint Maarten (Dutch part)",
            "Slovakia",
            "Slovenia",
            "Solomon Islands",
            "Somalia",
            "South Africa",
            "South Georgia and the South Sandwich Islands",
            "Korea (Republic of)",
            "South Sudan",
            "Spain",
            "Sri Lanka",
            "Sudan",
            "Suriname",
            "Svalbard and Jan Mayen",
            "Swaziland",
            "Sweden",
            "Switzerland",
            "Syrian Arab Republic",
            "Taiwan",
            "Tajikistan",
            "Tanzania (United Republic of)",
            "Thailand",
            "Timor-Leste",
            "Togo",
            "Tokelau",
            "Tonga",
            "Trinidad and Tobago",
            "Tunisia",
            "Turkey",
            "Turkmenistan",
            "Turks and Caicos Islands",
            "Tuvalu",
            "Uganda",
            "Ukraine",
            "United Arab Emirates",
            "United Kingdom of Great Britain and Northern Ireland",
            "United States of America",
            "Uruguay",
            "Uzbekistan",
            "Vanuatu",
            "Venezuela (Bolivarian Republic of)",
            "Viet Nam",
            "Wallis and Futuna",
            "Western Sahara",
            "Yemen",
            "Zambia",
            "Zimbabwe"
    };
}
