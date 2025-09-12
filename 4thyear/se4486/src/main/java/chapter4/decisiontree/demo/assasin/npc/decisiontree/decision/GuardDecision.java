package chapter4.decisiontree.demo.assasin.npc.decisiontree.decision;

import chapter4.decisiontree.demo.assasin.npc.decisiontree.action.GuardAction;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardDTNode;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardExtData;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardIntData;

public abstract class GuardDecision implements GuardDTNode {
    protected GuardDTNode trueBranch;
    protected GuardDTNode falseBranch;

    public GuardDecision(GuardDTNode trueBranch, GuardDTNode falseBranch) {
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    protected abstract GuardDTNode getBranch(GuardExtData extData, GuardIntData intData);

    @Override
    public GuardAction makeDecision(GuardExtData extData, GuardIntData intData) {
        GuardDTNode child = getBranch(extData,intData);
        return (GuardAction) child.makeDecision(extData,intData);
    }
}
