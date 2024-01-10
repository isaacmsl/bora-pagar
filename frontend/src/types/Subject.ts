import type { AppUser } from "./AppUser"

export type Subject = {
    code: string
    componentID: string
    name: string
    department: string
    totalHours: string
    interestedUsers: AppUser[]
}