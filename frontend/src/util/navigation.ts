import router from "@/router";

export function navigateToSubjectsOfUserGoogleId(googleId: string) {
    router.push(`/subjects-user/${googleId}`);
}