/// <reference path="Blocks/AllBlocks.ts" />
/// <reference path="Parser.ts" />

class BlockFactory {

    public createBlock(node: DiagramNode, outboundLinks: Link[], interpreter: Interpreter, 
                       robotModels: RobotModel[]): AbstractBlock {
        switch (node.getType()) {
            case "InitialNode":
                return new InitialBlock(node, outboundLinks);
            case "FinalNode":
                return new FinalBlock(node, outboundLinks);
            case "IfBlock":
                return new IfBlock(node, outboundLinks, interpreter);
            case "Function":
                return new FunctionBlock(node, outboundLinks, interpreter);                
            case "TrikV6EnginesForward":
                return new MotorsForwardBlock(node, outboundLinks, robotModels, interpreter);
            case "TrikV6EnginesBackward":
                return new MotorsBackwardBlock(node, outboundLinks, robotModels, interpreter);
            case "TrikV6EnginesStop":
                return new MotorsStopBlock(node, outboundLinks, robotModels);
            case "Timer":
                return new TimerBlock(node, outboundLinks, interpreter);
            case "SwitchBlock":
                return new SwitchBlock(node, outboundLinks, interpreter);
            case "MarkerUp":
                return new MarkerUpBlock(node, outboundLinks, robotModels);
            case "MarkerDown":
                return new MarkerDownBlock(node, outboundLinks, robotModels);
            case "VariableInit":
                return new VariableInitBlock(node, outboundLinks, interpreter);
            case "Randomizer":
                return new RandomInitBlock(node, outboundLinks, interpreter);
            case "FiBlock":
                return new EndIfBlock(node, outboundLinks);
            case "TrikSadSmile":
                return new TrikSadSmileBlock(node, outboundLinks, robotModels);
            case "TrikSmile":
                return new TrikSmileBlock(node, outboundLinks, robotModels);
            case "TrikSetPainterColor":
                return new TrikSetPainterColorBlock(node, outboundLinks, interpreter);
            case "TrikSetPainterWidth":
                return new TrikSetPainterWidthBlock(node, outboundLinks, interpreter);
            case "TrikDrawEllipse":
                return new TrikDrawEllipseBlock(node, outboundLinks, interpreter, robotModels);
            case "ClearScreen":
                return new ClearScreenBlock(node, outboundLinks, robotModels);
            case "TrikDrawPixel":
                return new TrikDrawPixelBlock(node, outboundLinks, interpreter, robotModels);
            case "TrikDrawLine":
                return new TrikDrawLineBlock(node, outboundLinks, interpreter, robotModels);
            case "TrikDrawRect":
                return new TrikDrawRectBlock(node, outboundLinks, interpreter, robotModels);
            case "TrikSetBackground":
                return new TrikSetBackgroundBlock(node, outboundLinks, robotModels);
            case "PrintText":
                return new PrintTextBlock(node, outboundLinks, interpreter, robotModels);
            default:
                throw new Error("Block " + node.getType() + " is not supported");
        }
    }

}