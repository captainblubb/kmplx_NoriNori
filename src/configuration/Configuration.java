package configuration;


public enum Configuration {
    instance;

    public static final int CELL_SIZE = 75;
    public static final int GRID_SIZE = 10;
    public static final int BORDER_THINKNESS = 4;

    public static final int CrossoverNoSolutionCounterMax = 1000;

    public static final int MutationChance = 30;
    public static final int MutationChanceTotal = 1000;

    public static final int Selection_CountOfSelections = 14;

    public static final int Turnament_Survivers = 20;

    public static final int initialPopolution = 30;

    public static final int Threadsleeptime = 500;
    public static int AmountOfSections=21;

}
