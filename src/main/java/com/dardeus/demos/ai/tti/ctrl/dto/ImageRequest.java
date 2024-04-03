package com.dardeus.demos.ai.tti.ctrl.dto;

/**
 * ImageRequest summary here...
 *
 * @author rbresler
 **/
public record ImageRequest(String prompt, int width, int height, int samples) {
}
