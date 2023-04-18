import ex.api.AnalysisService;
import service.*;

module Implementation {
    requires Api;
    provides AnalysisService with AccuracyService, BalancedAccuracy, F1ScoreService, KappaService;

}