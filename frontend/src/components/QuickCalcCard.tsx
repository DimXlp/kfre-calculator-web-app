import React, { useState } from "react";
import "../styles/components.css";

type Sex = "Male" | "Female";
type TabKey = "KFRE" | "CKD-EPI";

export default function QuickCalcCard() {
    const [tab, setTab] = useState<TabKey>("KFRE");

    // KFRE inputs
    const [age, setAge] = useState<number | "">("");
    const [sex, setSex] = useState<Sex>("Male");
    const [egfr, setEgfr] = useState<number | "">("");
    const [acr, setAcr] = useState<number | "">("");

    // CKD-EPI inputs
    const [ckdAge, setCkdAge] = useState<number | "">("");
    const [ckdSex, setCkdSex] = useState<Sex>("Male");
    const [scr, setScr] = useState<number | "">("");

    const handleCalcKFRE = (e: React.FormEvent) => {
        e.preventDefault();
        alert("KFRE preview only — no data is saved.");
    };

    const handleCalcCKD = (e: React.FormEvent) => {
        e.preventDefault();
        alert("CKD-EPI preview only — no data is saved.");
    };

    return (
        <section className="container" style={{ padding: "0 0 40px" }}>
            <div className="card">
                {/* Header */}
                <div style={{ display: "flex", flexDirection: "column", gap: 12, marginBottom: 12 }}>
                    <div>
                        <p className="section-title">Quick Calculation</p>
                        <p className="muted">Instant preview — no sign-in, nothing is stored.</p>
                    </div>

                    {/* Full-width segmented tabs */}
                    <div className="tabs tabs-full" role="tablist" aria-label="Calculator type">
                        <button
                            type="button"
                            className={`tab ${tab === "KFRE" ? "tab-active" : ""}`}
                            onClick={() => setTab("KFRE")}
                            role="tab"
                            aria-selected={tab === "KFRE"}
                        >
                            KFRE
                        </button>
                        <button
                            type="button"
                            className={`tab ${tab === "CKD-EPI" ? "tab-active" : ""}`}
                            onClick={() => setTab("CKD-EPI")}
                            role="tab"
                            aria-selected={tab === "CKD-EPI"}
                        >
                            CKD-EPI
                        </button>
                    </div>
                </div>

                {/* Forms (stacked fields) */}
                {tab === "KFRE" ? (
                    <form onSubmit={handleCalcKFRE} className="form-stack form-narrow">
                        <label>
                            <div className="label">Age (years)</div>
                            <input
                                className="input input-sm"
                                inputMode="numeric"
                                placeholder="e.g. 65"
                                value={age}
                                onChange={(e) => setAge(e.target.value === "" ? "" : Number(e.target.value))}
                            />
                        </label>

                        <label>
                            <div className="label">Sex</div>
                            <select
                                className="input input-sm"
                                value={sex}
                                onChange={(e) => setSex(e.target.value as Sex)}
                            >
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select>
                        </label>

                        <label>
                            <div className="label">eGFR (mL/min/1.73m²)</div>
                            <input
                                className="input input-sm"
                                inputMode="numeric"
                                placeholder="e.g. 32"
                                value={egfr}
                                onChange={(e) => setEgfr(e.target.value === "" ? "" : Number(e.target.value))}
                            />
                        </label>

                        <label>
                            <div className="label">ACR (mg/g)</div>
                            <input
                                className="input input-sm"
                                inputMode="numeric"
                                placeholder="e.g. 160"
                                value={acr}
                                onChange={(e) => setAcr(e.target.value === "" ? "" : Number(e.target.value))}
                            />
                        </label>

                        <div style={{ marginTop: 6 }}>
                            <button type="submit" className="btn btn-primary">Calculate</button>
                        </div>
                    </form>
                ) : (
                    <form onSubmit={handleCalcCKD} className="form-stack form-narrow">
                        <label>
                            <div className="label">Age (years)</div>
                            <input
                                className="input input-sm"
                                inputMode="numeric"
                                placeholder="e.g. 65"
                                value={ckdAge}
                                onChange={(e) => setCkdAge(e.target.value === "" ? "" : Number(e.target.value))}
                            />
                        </label>

                        <label>
                            <div className="label">Sex</div>
                            <select
                                className="input input-sm"
                                value={ckdSex}
                                onChange={(e) => setCkdSex(e.target.value as Sex)}
                            >
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select>
                        </label>

                        <label>
                            <div className="label">Serum Creatinine (mg/dL)</div>
                            <input
                                className="input input-sm"
                                inputMode="numeric"
                                placeholder="e.g. 1.4"
                                value={scr}
                                onChange={(e) => setScr(e.target.value === "" ? "" : Number(e.target.value))}
                            />
                        </label>

                        <div style={{ marginTop: 6 }}>
                            <button type="submit" className="btn btn-primary">Calculate</button>
                        </div>
                    </form>
                )}
            </div>
        </section>
    );
}
