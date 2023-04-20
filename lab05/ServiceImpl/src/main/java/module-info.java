import ex.api.AnalysisService;

module ServiceImpl {
    exports ex.api;
    exports service;
    provides AnalysisService with service.AccuracyService, service.KappaService, service.F1ScoreService, service.BalancedAccuracy;
}