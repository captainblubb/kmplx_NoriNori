package configuration;


public enum Configuration {
    instance;

    public static final int CELL_SIZE = 75;
    public static final int GRID_SIZE = 10;
    public static final int BORDER_THINKNESS = 4;

    public static final int CrossoverNoSolutionCounterMax = 1000;

    public static final int MutationChance = 10;
    public static final int MutationChanceTotal = 100;

    public static final int Selection_CountOfSelections = 64;

    public static final int Turnament_Survivers = 50;  // min 3

    public static final int initialPopolution = 150;

    public static final int Threadsleeptime = 70;

    public static int AmountOfSections=0;

}
