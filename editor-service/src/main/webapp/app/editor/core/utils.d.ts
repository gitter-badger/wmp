/*
 * Copyright Semen Yuryev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

declare class RGBAColor {

    alpha: number;
    rgb: string;
    public constructor(alpha: number, rgb: string);
}

declare class ColorUtils {

    public static getRBGAColor(color: string): RGBAColor;
    public static alphaHexTo0To1(alpha: string): number;
}

declare class MathUtils {

    public static toDeg(radians: number): number;
    public static toRad(degrees: number): number;
    public static twoPointLenght(x1: number, y1: number, x2: number, y2: number): number;
    public static sqr(x: number): number;
    public static min(a: number, b: number): number;
    public static toRadians(angle : number): number;
    public static toDegrees(angle : number): number;
    public static rotateVector(x: number, y: number, angle: number): {x: number, y: number};
}

declare class StringUtils {

    public static format(str: string, ...args: string[]): string;
    public static getInputStringSize(input): number;
}

declare class XmlHttpFactory {

    public static createXMLHTTPObject(): any;
}

