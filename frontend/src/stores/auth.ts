import { UserService } from '@/services/UserService';
import type { GoogleUserInfo } from '@/types/GoogleUserInfo';
import { defineStore } from 'pinia';
import { decodeCredential, googleLogout } from 'vue3-google-login';

const userService = new UserService();

const credentialKeyName = 'credential';

function normalizeGivenName(name : String) {
  return name.charAt(0) + name.slice(1).toLowerCase();
}

export const useAuthStore = defineStore('auth', {
  state: () => {
    return { user: undefined as GoogleUserInfo | undefined }
  },
  actions: {
    setUserFromCredential(credential : string) {
      this.user = decodeCredential(credential) as GoogleUserInfo;
      this.user.given_name = normalizeGivenName(this.user.given_name);
    },
    googleLoginCallback(response : any) {
      localStorage.setItem(credentialKeyName, response.credential);
      this.setUserFromCredential(response.credential);
      userService.welcomeUser(response.credential);
    },
    getCredentialFromLocalStorage() {
      const credential = localStorage.getItem(credentialKeyName);
      if (credential) {
        try {
          this.setUserFromCredential(credential);
        } catch (error) {
          this.logout();
        }
      }
    },
    logout() {
      localStorage.removeItem(credentialKeyName);
      this.user = undefined;
      googleLogout();
    },
    loggedIn() {
      return this.user != undefined;
    }
  },
})