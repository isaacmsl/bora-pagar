import router from "@/router";
import type { AppUser } from "@/types/AppUser";

export function navigateToSubjectsOfUser(user: AppUser) {
    router.push(`/subjects-user/${user.googleId}`);
}