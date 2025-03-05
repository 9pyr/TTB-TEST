import isSSR from "@/utils/isSSR"

export default function removeLocalStorage(key: string) {
  if (isSSR()) {
    window.localStorage.removeItem(key)
  }
}
