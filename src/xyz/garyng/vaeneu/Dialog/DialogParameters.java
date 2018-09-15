package xyz.garyng.vaeneu.Dialog;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DialogParameters
{
    @Builder.Default
    private String title = "Are you sure?";
    @Builder.Default
    private String body = "Default body text";
    @Builder.Default
    private String acceptedText = "OK";
    @Builder.Default
    private String rejectedText = "CANCEL";
    @Builder.Default
    private Runnable onAccepted = () ->
    {
    };
    @Builder.Default
    private Runnable onRejected = () ->
    {
    };
}
