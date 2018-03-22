package configuration;


public enum Configuration {
    instance;

    public static final int CELL_SIZE = 75;
    public static final int GRID_SIZE = 10;
    public static final int BORDER_THINKNESS = 4;

    public static final int CrossoverNoSolutionCounterMax = 1000;

    public static final int MutationChance = 800;
    public static final int MutationChanceTotal = 1000;

    public static final int Selection_CountOfSelections = 40;

    public static final int Turnament_Survivers = 50;  // min 3

    public static final int initialPopolution = 100;

    public static final int Threadsleeptime = 100;

    public static int AmountOfSections=0;

    public static final int CrossoverKCutsMinCuts = 1;

    public static final int CrossoverKCutsMaxCuts = 6;

}
