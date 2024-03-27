package RefereeSite;

public interface IMRefereeSite_TReferee {
    
    public void announceNewGame();

    public void callTrial();
    
    public void startTrial();

    public void assertTrialDecision();
    
    public void declareGameWinner(int numbergames);        
    
    public void declareMatchWinner();
    
}
